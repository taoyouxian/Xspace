package cn.edu.ruc.iir.xpace.datascience;

import org.apache.spark.SparkConf;
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
public class Cost {

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
//                .master("spark://10.77.40.236:7077")
                .master("local")
                .appName("spark-demo")
                .config("spark.executor.memory", "4g")
                .config("spark.driver.memory", "2g")
                .config("spark.executor.cores", 4)
                .config("spark.sql.warehouse.dir", "hdfs://10.77.40.236:9000/spark-warehouse")
                .config("spark.storage.memoryFraction", "0")
                //.config("spark.driver.maxResultSize", "1g")
                .getOrCreate();
        SparkConf conf = new SparkConf().setAppName("spark WordCount!")
                .setMaster("local")
//                .setSparkHome("hdfs://10.77.40.236:9000/spark-warehouse")
                ;
        // Create an RDD
        JavaRDD<String> peopleRDD = spark.sparkContext()
                .textFile("/home/presto/opt/tmp_data/tpc_h/10G/lineitem.tbl", 1)
                .toJavaRDD();

// The schema is encoded in a string
        String column = "L_ORDERKEY L_PARTKEY L_SUPPKEY L_LINENUMBER L_QUANTITY L_EXTENDEDPRICE L_DISCOUNT L_TAX L_RETURNFLAG L_LINESTATUS L_SHIPDATE L_COMMITDATE L_RECEIPTDATE L_SHIPINSTRUCT L_SHIPMODE L_COMMENT";
        String colType = "INTEGER INTEGER INTEGER INTEGER FLOAT FLOAT FLOAT FLOAT CHAR CHAR DATE DATE DATE CHAR CHAR VARCHAR";
//        String column = "R_REGIONKEY R_NAME R_COMMENT";
//        String colType = "INTEGER CHAR VARCHAR";

        List<StructField> fields = new ArrayList<StructField>();
        StructField field = new StructField();
        String[] attribute = column.toLowerCase().split(" ");
        String[] type = colType.toLowerCase().split(" ");
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
        JavaRDD<String> jRDD = spark.sparkContext()
                .textFile("/home/presto/opt/tmp_data/tpc_h/10G/lineitem.tbl", 1)
                .toJavaRDD();
//        JavaRDD<String> jRDD = spark.sparkContext()
//                .textFile("/home/presto/opt/tmp_data/tpc_h/10G/region.tbl", 1)
//                .toJavaRDD();

        JavaRDD<Row> rowRDD = jRDD.map(new Function<String, Row>() {
            public Row call(String s) throws Exception {
                String[] attributes = s.split("\\|");
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

        Dataset<Row> peopleDataFrame = spark.createDataFrame(rowRDD, schema);

// Creates a temporary view using the DataFrame
        peopleDataFrame.createOrReplaceTempView("LINEITEM");

// SQL can be run over a temporary view created using DataFrames
        Dataset<Row> results = spark.sql("SELECT count(*) as count FROM LINEITEM");

        results.show();
    }
}
