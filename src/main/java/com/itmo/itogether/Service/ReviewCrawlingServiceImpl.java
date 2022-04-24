package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.InformationDTO;
import com.itmo.itogether.DTO.ReviewDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReviewCrawlingServiceImpl implements ReviewCrawlingService {

    @Override
    public List<ReviewDTO> getNaverReviews(List<InformationDTO> informationDTO) throws IOException {
        List<ReviewDTO> reviewDTO = new ArrayList<ReviewDTO>();

        for (InformationDTO information : informationDTO) {
            String REVIEWURL = null;

            if (String.valueOf(information.getInformationId()).charAt(0) == '1') {
                REVIEWURL = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=" + information.getInformationTitle() + " 동아리 후기";
            } else {
                REVIEWURL = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=" + information.getInformationTitle() + " 후기";
            }


            Document doc = Jsoup.connect(REVIEWURL).get();
            Elements contents = doc.select("#main_pack div.total_area > a");

            for (Element content : contents) {
                String title = content.text();
                String url = content.attr("href");

                if (String.valueOf(information.getInformationId()).charAt(0) == '1') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && title.contains("동아리")) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '2') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("교육") || title.contains("강의") || title.contains("합격"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '3') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("세미나") || title.contains("참관") || title.contains("방문"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '4') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("합격") || title.contains("독학") || title.contains("자격증"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '5') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("합격") || title.contains("수료") || title.contains("챌린지"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기")) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                }
            }
        }
        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> getTistoryReviews(List<InformationDTO> informationDTO) throws IOException {
        List<ReviewDTO> reviewDTO = new ArrayList<ReviewDTO>();

        for (InformationDTO information : informationDTO) {
            try {
                TimeUnit.MILLISECONDS.sleep(2200);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            String REVIEWURL = "https://www.google.com/search?q=" + information.getInformationTitle() + " 후기 site:tistory.com";

            Document doc = Jsoup.connect(REVIEWURL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .get();

            Elements contents = doc.select("#rso div.NJo7tc.Z26q7c.jGGQ5e > div > a");

            for (Element content : contents) {
                String title = content.text();
                String url = content.attr("href");

                if (String.valueOf(information.getInformationId()).charAt(0) == '1') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && title.contains("동아리")) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '2') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("교육") || title.contains("강의") || title.contains("합격"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '3') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("세미나") || title.contains("참관") || title.contains("방문"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '4') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("합격") || title.contains("독학") || title.contains("자격증"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '5') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("합격") || title.contains("수료") || title.contains("챌린지"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기")) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(2200);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }

        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> getVelogReviews(List<InformationDTO> informationDTO) throws IOException {
        List<ReviewDTO> reviewDTO = new ArrayList<ReviewDTO>();

        for (InformationDTO information : informationDTO) {
            try {
                TimeUnit.MILLISECONDS.sleep(2200);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            String REVIEWURL = "https://www.google.com/search?q=" + information.getInformationTitle() + " 후기 site:velog.io";

            Document doc = Jsoup.connect(REVIEWURL).get();
            Elements contents = doc.select("#rso div.NJo7tc.Z26q7c.jGGQ5e > div > a");

            for (Element content : contents) {
                String title = content.text();
                String url = content.attr("href");

                if (String.valueOf(information.getInformationId()).charAt(0) == '1') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && title.contains("동아리")) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '2') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("교육") || title.contains("강의") || title.contains("합격"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '3') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("세미나") || title.contains("참관") || title.contains("방문"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '4') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("합격") || title.contains("독학") || title.contains("자격증"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else if (String.valueOf(information.getInformationId()).charAt(0) == '5') {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기") && (title.contains("합격") || title.contains("수료") || title.contains("챌린지"))) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                } else {
                    if (title.contains(information.getInformationTitle()) && title.contains("후기")) {
                        System.out.println("후기 제목: " + title + " 링크: " + url + "\n");
                        reviewDTO.add(new ReviewDTO(null, information.getInformationId(), title, url, "naver"));
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(2200);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
        return reviewDTO;
    }
}
