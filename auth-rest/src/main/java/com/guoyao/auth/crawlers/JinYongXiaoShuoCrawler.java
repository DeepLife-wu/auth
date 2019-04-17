package com.guoyao.auth.crawlers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.guoyao.auth.model.BookChapter;
import com.guoyao.auth.model.BookType;
import com.guoyao.auth.model.Bookinfo;
import com.guoyao.auth.repository.BookChapterRepository;
import com.guoyao.auth.repository.BookTypeRepository;
import com.guoyao.auth.repository.BookinfoRepository;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;

@Crawler(name = "jinYongXiaoShuoCrawler")
public class JinYongXiaoShuoCrawler extends BaseSeimiCrawler {
	
	private static final String jinYongHost = "http://www.jinyongwang.com";
	
	@Autowired
	private BookTypeRepository bookTypeRepository;
	@Autowired
	private BookinfoRepository bookinfoRepository;
	@Autowired
	private BookChapterRepository bookChapterRepository;
	
	private Map<String,Bookinfo> bookMap = new LinkedHashMap<>();
	private Map<String,BookChapter> bookChapterMap = new LinkedHashMap<>();
	
    @Override
    public String[] startUrls() {
        return new String[]{jinYongHost};
    }

    @Override
    public void start(Response response) {
    	BookType bookType = null;
    	try {
    		if(CollectionUtils.isEmpty(bookTypeRepository.findAll())) {
    			bookType = new BookType();//(id, name, note, bookinfos);
    			bookType.setName("武侠");
    			bookType.setNote("武侠是华人界特有的一种流行文化。武侠文化以各式侠客为主角，以神乎其神的武术技巧为特点，刻画宣扬侠客精神。\r\n" + 
    					"武侠与儒家和道家在文化上有一定的联系。武侠按时间分有古代和民国武侠，按流派分有新、旧以及古仙武侠，武侠作者有19世纪的梁羽生、金庸、古龙等，当代的以及其他时期的作家。");
    			bookTypeRepository.save(bookType);
    		} 
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        JXDocument doc = response.document();
        try {
//            List<Object> bookUrlList = doc.sel("//*[@id='book_ul']/li[@class='book_li']/p[@class='book_li_img']/a/@href");
            List<Object> bookUrlList = doc.sel("//*[@id='book_ul']/li[@class='book_li']/p[@class='book_li_title']/a/@href");
            List<Object> bookUrlTitle = doc.sel("//*[@id='book_ul']/li[@class='book_li']/p[@class='book_li_title']/a/text()");
            
            logger.info("共{}本书", bookUrlList.size());
            
            if(!CollectionUtils.isEmpty(bookUrlList) && !CollectionUtils.isEmpty(bookUrlTitle)) {
            	for(int i = 0;i < bookUrlList.size(); i++) {
            		Bookinfo bookinfo = new Bookinfo();
                	if(bookType != null)
                		bookinfo.setBookType(bookType);
                	bookinfo.setName(bookUrlTitle.get(i).toString());
                	bookinfo.setAuthor("金庸");
                	bookinfo.setNote("");
                	bookinfoRepository.save(bookinfo);
                	
                	bookMap.put(jinYongHost + bookUrlList.get(i).toString(), bookinfo);
                	push(Request.build(jinYongHost + bookUrlList.get(i).toString(),JinYongXiaoShuoCrawler::getTitle));
            	}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getTitle(Response response){
    	Bookinfo bookinfo = bookMap.get(response.getUrl());
    	
        JXDocument doc = response.document();
        try {
            List<Object> chapterUrlList = doc.sel("//*[@id=\"pu_box\"]/div[@class='main']/ul[@class='mlist']/li/a/@href");
			logger.info("书籍链接{} 每本书共{}章节", response.getUrl(), chapterUrlList.size());
            List<Object> titleList = doc.sel("//*[@id=\"pu_box\"]/div[@class='main']/ul[@class='mlist']/li/a/text()");
            
            if(!CollectionUtils.isEmpty(chapterUrlList) && !CollectionUtils.isEmpty(titleList)) {
            	for(int i = 0;i < chapterUrlList.size(); i++) {
            		logger.info("\t章节链接{} 标题{}", chapterUrlList.get(i), titleList.get(i));
            		
            		BookChapter bookChapter = new BookChapter();//(id, bookinfo, content, title);
            		if(bookinfo != null) bookChapter.setBookinfo(bookinfo);
            		bookChapter.setTitle(titleList.get(i).toString());
            		
//            		bookChapterRepository.save(bookChapter);
            		
            		bookChapterMap.put(jinYongHost + chapterUrlList.get(i), bookChapter);
            		push(Request.build(jinYongHost + chapterUrlList.get(i),JinYongXiaoShuoCrawler::getContent));
            	}
            }
            
            /*//do something
			for (Object s : chapterUrlList){
			    push(Request.build(jinYongHost + s.toString(),Basic::getContent));
			}*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    //*[@id='vcon']/p/text()
    */
    public void getContent(Response response){
    	BookChapter bookChapter = bookChapterMap.get(response.getUrl());
    	
        JXDocument doc = response.document();
        try {
            List<Object> contentList = doc.sel("//*[@id='vcon']/p/text()");
//			logger.info("章节url:{} 每本书共{}章节", response.getUrl(), urls);
            
            StringBuilder sb = new StringBuilder();
            for(Object content : contentList) {
            	logger.info(content.toString());
            	sb.append(content.toString());
            }
            bookChapter.setContent(sb.toString());
            bookChapterRepository.save(bookChapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}