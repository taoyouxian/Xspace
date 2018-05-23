package cn.edu.ruc.iir.xpace.datascience;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xpace.datascience
 * @ClassName: Main
 * @Description: Spark cache function (-Dspark.master=local)
 * @author: taoyouxian
 * @date: Create in 2018-05-07 14:16
 **/
public class Main {

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .master("spark://10.77.40.236:7077")
//                .master("local")
                .appName("spark-cache")
                .config("spark.executor.memory", "4g")
                .config("spark.driver.memory", "2g")
                .config("spark.executor.cores", 4)
//                .config("spark.sql.warehouse.dir", "hdfs://10.77.40.236:9000/spark-warehouse")
                .config("spark.storage.memoryFraction", "0")
                //.config("spark.driver.maxResultSize", "1g")
                .getOrCreate();

        ConfigFactory instance = ConfigFactory.Instance();
        instance.loadProperties(args[0]);

        String oneCol = instance.getProperty("oneCol");
        String oneColType = instance.getProperty("oneColType");
        String onePath = instance.getProperty("onePath");

        String secCol = instance.getProperty("secCol");
        String secColType = instance.getProperty("secColType");
        String secPath = instance.getProperty("secPath");

        String cache = instance.getProperty("cache");
        String joinKey = instance.getProperty("joinKey");
        String dfShow = instance.getProperty("dfShow");

        Dataset<Row> oneDF = getRowDataset(spark, oneCol, oneColType, onePath);
        Dataset<Row> secDF = getRowDataset(spark, secCol, secColType, secPath);

//        Dataset<Row> joinDs = oneDF.join(oneDF, "r_regionkey");
//        Seq<String> joinCol = new Set.Set2<>("r_regionkey", "n_regionkey").toSeq();

        String cacheTag = "no";
        if (cache.equals("true")) {
            oneDF.cache();
//            secDF.cache();
            cacheTag = "yes";
        }

        long b = System.currentTimeMillis();
        Dataset<Row> joinDs = oneDF.join(secDF, joinKey);
        long e = System.currentTimeMillis();
        System.out.println(joinDs.count());
        long end = System.currentTimeMillis();
        System.out.println("join(): " + (e - b));
        System.out.println("count(): " + (end - e));
        System.out.println("join() + count(): " + (end - b));
        System.out.println("cache: " + cache + " " + cacheTag);

        if (dfShow.equals("true"))
            joinDs.show();

    }

    private static Dataset<Row> getRowDataset(SparkSession spark, String column, String columnType, String onePath) {
        List<StructField> fields = new ArrayList<StructField>();
        StructField field = new StructField();
        String[] attribute = column.toLowerCase().split(" ");
        String[] type = columnType.toLowerCase().split(" ");
        int i = 0;
        for (String fieldName : attribute) {
            if (type[i].equals("string") || type[i].equals("char") || type[i].equals("varchar")) {
                field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
            } else if (type[i].equals("float") || type[i].equals("double")) {
                field = DataTypes.createStructField(fieldName, DataTypes.FloatType, true);
            } else if (type[i].equals("int") || type[i].equals("integer")) {
                field = DataTypes.createStructField(fieldName, DataTypes.IntegerType, true);
            } else if (type[i].equals("date")) {
//                field = DataTypes.createStructField(fieldName, DataTypes.DateType, true);
                field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
            }
            fields.add(field);
            i++;
        }
        StructType schema = DataTypes.createStructType(fields);

        JavaRDD<String> javaRDD = spark.sparkContext()
                .textFile(onePath, 1)
                .toJavaRDD();

        JavaRDD<Row> rowRDD = javaRDD.map(new Function<String, Row>() {
            public Row call(String line) throws Exception {
                String[] attributes = line.split("\\|");
                Object[] o = new Object[attributes.length];
                // if (index_flag) {
                int i = 0;
                for (String value : attributes) {
                    if (type[i].equals("string") || type[i].equals("char") || type[i].equals("varchar")) {
                        o[i] = value.trim();
                    } else if (type[i].equals("float") || type[i].equals("double")) {
                        o[i] = Float.valueOf(value.trim());
                    } else if (type[i].equals("int") || type[i].equals("integer")) {
                        o[i] = Integer.valueOf(value.trim());
                    } else if (type[i].equals("date")) {
                        o[i] = value.trim();
                    }
                    i++;
                }
                return RowFactory.create(o);
            }
        });
        return spark.createDataFrame(rowRDD, schema);
    }
}
