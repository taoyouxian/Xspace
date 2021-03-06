package cn.edu.ruc.iir.xspace.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.lucene
 * @ClassName: IndexTest
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-04-02 15:26
 **/
public class IndexTest {

    private String ids[] = {"1", "2", "3"};
    private String citys[] = {"qingdao", "nanjing", "shanghai"};
    private String descs[] = {
            "Qingdao is a beautiful city.",
            "Nanjing is a city of culture.",
            "Shanghai is a bustling city."
    };
    private Directory dir;

    @Before
    public void setUp() throws Exception {
        dir = FSDirectory.open(Paths.get("D:\\blog\\lucene2"));
        IndexWriter writer = getWriter();
        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            doc.add(new StringField("id", ids[i], Field.Store.YES));
            doc.add(new StringField("city", citys[i], Field.Store.YES));
            doc.add(new TextField("desc", descs[i], Field.Store.NO));
            writer.addDocument(doc);
        }
        writer.close();
    }

    /**
     * 测试写了几个文档
     *
     * @throws Exception
     */
    @Test
    public void testIndexWriter() throws Exception {
        IndexWriter writer = getWriter();
        System.out.println("写入" + writer.maxDoc() + "文档");
    }

    /**
     * 测试读取文档
     *
     * @throws IOException
     */
    @Test
    public void testIndexReader() throws IOException {
        IndexReader reader = DirectoryReader.open(dir);
        System.out.println("最大文档数:" + reader.maxDoc());
        System.out.println("实际文档数:" + reader.numDocs());
        reader.close();
    }

    /**
     * 删除文档在合并前(索引不会删除，只是做个标记)
     *
     * @throws Exception
     */
    @Test
    public void testDeleteBeforeMerge() throws Exception {
        IndexWriter writer = getWriter();
        System.out.println("删除前:" + writer.numDocs());
        //根据条件删
        writer.deleteDocuments(new Term("id", "1"));
        writer.commit();
        System.out.println("最大文档数:" + writer.maxDoc());
        System.out.println("实际文档数:" + writer.numDocs());
        writer.close();
    }

    /**
     * 删除文档在合并后(索引删除)
     *
     * @throws Exception
     */
    @Test
    public void testDeleteAfterMerge() throws Exception {
        IndexWriter writer = getWriter();
        System.out.println("删除前:" + writer.numDocs());
        //根据条件删
        writer.deleteDocuments(new Term("id", "1"));
        writer.forceMergeDeletes();//强制删除
        writer.commit();
        System.out.println("最大文档数:" + writer.maxDoc());
        System.out.println("实际文档数:" + writer.numDocs());
        writer.close();
    }

    /**
     * 测试更新(比较耗时，底层先删除再创键)
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id", "1", Field.Store.YES));
        doc.add(new StringField("city", "qingdao", Field.Store.YES));
        doc.add(new TextField("desc", "this is a city", Field.Store.NO));
        writer.updateDocument(new Term("id", "1"), doc);
        writer.close();
    }

    /**
     * IndexWriter实例
     *
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter() throws Exception {
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, iwc);
        return writer;
    }
}
