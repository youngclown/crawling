package com.jsoup.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class CrawlingApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(CrawlingApplication.class, args);
        String URL = "http://gmoney.or.kr/main/gmoney/searchFranchisee.do";
        File f = new File("C:\\home\\text2.txt");
        FileWriter fw = new FileWriter(f, true);


        for (int z = 1; z < 55; z++) {
            Document doc = Jsoup.connect(URL)
                    .data("apiKey", "E4A59B05-0CF4-3654-BD0C-A169F70CCB34")
                    .data("pageIndex", z+"") // 1 ~ 54
                    .data("sigoonCode", "고양시")
                    .post();
//        Elements elementList = doc.select(".tb_type2");

            Element table = doc.select("table").get(0); //select the first table.
            Elements rows = table.select("tr");
//        ArrayList<String> downServers = new ArrayList<>();
            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");
                StringBuffer buffer = new StringBuffer();
                for (Element col : cols) { //first row is the col names so skip it.
                    buffer.append(col.text());
                    buffer.append("\t");
                }
                buffer.append("\n");
                fw.write(buffer.toString());
            }

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        fw.close();
    }
}
