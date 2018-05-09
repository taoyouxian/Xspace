package cn.edu.ruc.iir.xpace.datascience

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object cacheTest {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local")
      .appName("cacheTest")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    //    val table1 = args(0)
    //    val table2 = args(1)
    //    val resultDest1 = args(2)
    //    val resultDest2 = args(3)
    //test
    ///Users/mark/Documents/MasterLife/BigData
    val aSet = spark.read.text("hdfs://202.112.113.71:9000/hw/customer.tbl")
    val bSet = spark.read.text("hdfs://202.112.113.71:9000/hw/orders.tbl")
    //    val aSet = spark.read.text(table1)
    //    val bSet = spark.read.text(table2)

    import spark.implicits._


    val aaSet = aSet.rdd.map {
      line => {
        val splits = line.mkString.split("\\|")
        (splits(0), splits(1), splits(2), splits(3), splits(4), splits(5), splits(6), splits(7)) //return value
      }
    }.toDF("C_CUSTKEY", "C_NAME", "C_ADDRESS", "C_NATIONKEY", "C_PHONE", "C_ACCTBAL", "C_MKTSEGMENT", "C_COMMENT")

    val bbSet = bSet.rdd.map {
      line => {
        val splits = line.mkString.split("\\|")
        (splits(0), splits(1), splits(2), splits(3), splits(4), splits(5), splits(6), splits(7), splits(8)) //return value
      }
    }.toDF("O_ORDERKEY", "O_CUSTKEY", "O_ORDERSTATUS", "O_TOTALPRICE", "O_ORDERDATE", "O_ORDERPRIORITY", "O_CLERK", "O_SHIPPRIORITY", "O_COMMENT")

    //    aaSet.cache()
    //    bbSet.cache()


    val cSet = aaSet.join(bbSet, $"C_CUSTKEY" === $"O_CUSTKEY")
    cSet.show()


    val dSet = aaSet.join(bbSet, $"C_CUSTKEY" === $"O_CUSTKEY")
    //    dSet.rdd.saveAsTextFile(resultDest2)
    dSet.show()


    //      aaSet.saveAsTextFile("/Users/mark/Documents/MasterLife/BigData/testfile/result1")
    //    aaSet.registerTempTable("customer")
    //    bbSet.registerTempTable("order")
    //      spark.sql("select * from ta1 limit 10").show()
    //    val bbSet =
    //      cSet.saveAsTextFile("/Users/mark/Documents/MasterLife/BigData/testfile/result6")

    //    val aSet = spark.read.csv("hdfs://202.112.113.71:9000/hw/a.csv").toDF("statis_day","fseq_no","fuin","facct_type","fapp_id","fapp_sub_id","fserial_no","fwater_type","ftran_type","fio_flag","fstatus","fex_uin","fex_acct_type","ftran_amt","fbalance","foverdraft","fout_credit","fin_credit","ftran_time","fchg_time","fapp_name","fapp_ip","fuser_ip","fchn_biz","fchn_voucher","fchn_type","fchn_sub_type","fchn_serialno","fcur_code","fbiz_code","fcancel_serialno","fpeer_acct","fuser_id","ftran_info","ftran_remark")
    //
    //    val bSet = spark.read.csv("hdfs://202.112.113.71:9000/hw/b.csv").toDF("statis_day","fseq_no","fuin","facct_type","fapp_id","fapp_sub_id","fserial_no","fwater_type","ftran_type","fio_flag","fstatus","fex_uin","fex_acct_type","ftran_amt","fbalance","foverdraft","fout_credit","fin_credit","ftran_time","fchg_time","fapp_name","fapp_ip","fuser_ip","fchn_biz","fchn_voucher","fchn_type","fchn_sub_type","fchn_serialno","fcur_code","fbiz_code","fcancel_serialno","fpeer_acct","fuser_id","ftran_info","ftran_remark")
    //
    //
    //    val cSet = aSet.join(bSet,"fuin")
    //    cSet.show()

  }

}
