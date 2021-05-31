import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.cool.EsApplication;
import com.cool.entity.Blog;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author water33
 */
@SpringBootTest(classes = EsApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EsTest {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;


    @Test
    public void testCreate() throws IOException {
        //1.创建请求
        CreateIndexRequest request = new CreateIndexRequest("blog");

        //2.使用客户端调用
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(response.index());
    }

    @Test
    public void testExist() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("test");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    @Test
    public void testDel() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("test");
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        if(delete.isAcknowledged()){
            System.out.println("删除索引成功");
        }
    }


    @Test
    public void testAddDoc() throws IOException {
        Blog blog = new Blog();
        blog.setAuthor("lucy");
        blog.setContent("这是一篇关于elasticsearch的短文,不好看不收钱");
        //blog.setCreateTime(LocalDateTime.now());
        blog.setTitle("测试elasticsearch标题");


        IndexRequest request = new IndexRequest("blog");

        request.id("46e63c5098b6236822a4cbf584991c34");
        request.timeout(TimeValue.timeValueSeconds(2));
        request.timeout("1s");

        request.source(JSON.toJSONString(blog), XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response.status());
        System.out.println(response.toString());
    }

    @Test
    public void testGet() throws IOException {
        GetRequest request = new GetRequest("blog","46e63c5098b6236822a4cbf584991c34");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        Map<String, Object> source = response.getSource();
        source.forEach((k,v)->{
            System.out.println(k+"--"+v);
        });
        System.out.println(response);
    }

    @Test
    public void testSearch() throws IOException {
        SearchRequest request = new SearchRequest("blog");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("content","短文"));
        builder.timeout(new TimeValue(10, TimeUnit.SECONDS));

        request.source(builder);

        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> map = hit.getSourceAsMap();

        }
        List<Blog> blogs = Arrays.asList(hits).stream()
                .map((hit) -> {
                    Map<String, Object> map = hit.getSourceAsMap();
                    Blog b = new Blog();
                    b.setId(MapUtil.getStr(map, "id"));
                    b.setTitle(MapUtil.getStr(map, "title"));
                    b.setAuthor(MapUtil.getStr(map, "author"));
                    b.setContent(MapUtil.getStr(map, "content"));
                    ZoneId zoneId = ZoneId.systemDefault();
                    LocalDateTime createTime = LocalDateTime.ofInstant(MapUtil.getDate(map, "create_time").toInstant(), zoneId);
                    b.setCreateTime(createTime);

                    LocalDateTime updateTime = LocalDateTime.ofInstant(MapUtil.getDate(map, "update_time").toInstant(), zoneId);
                    b.setUpdateTime(updateTime);
                    return b;
                }).collect(Collectors.toList());

        blogs.forEach(b->{
            System.out.println(JSON.toJSONString(b));
        });

    }


    @Test
    public void testTime(){

        Blog blog = new Blog();
        blog.setAuthor("lucy");
        blog.setContent("这是一篇关于elasticsearch的短文,不好看不收钱");
        blog.setCreateTime(LocalDateTime.now());
        blog.setTitle("测试elasticsearch标题");

        System.out.println(JSON.toJSONString(blog));
    }

}
