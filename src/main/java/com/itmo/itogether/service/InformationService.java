package com.itmo.itogether.service;

import com.itmo.itogether.domain.Field;
import com.itmo.itogether.domain.Information;
import com.itmo.itogether.domain.InformationQualification;
import com.itmo.itogether.domain.RecruitmentField;
import com.itmo.itogether.repository.information.InformationRepositoryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformationService {
    private final InformationRepositoryMapper informationRepositoryMapper;

    public InformationService(InformationRepositoryMapper informationRepositoryMapper) {
        this.informationRepositoryMapper = informationRepositoryMapper;
    }

    public Information findOneInformation(int informationId) {
        Information result = informationRepositoryMapper.findByInfoId(informationId);
        List<String> f = new ArrayList<String>();
        List<String> q = new ArrayList<String>();
        for (RecruitmentField recruitmentField : result.getRecruitmentField()) {
            if (recruitmentField.getFieldId() == 0){
                f.add(null);
            }
            for (Field field : recruitmentField.getField()) {
                if (field.getFieldId() == 1) {
                    f.add("프론트엔드");
                } else if (field.getFieldId() == 2) {
                    f.add("백엔드");
                } else if (field.getFieldId() == 3) {
                    f.add("ios");
                } else if (field.getFieldId() == 4) {
                    f.add("안드로이드");
                } else if (field.getFieldId() == 5) {
                    f.add("인공지능");
                } else if (field.getFieldId() == 6) {
                    f.add("데이터처리");
                } else if (field.getFieldId() == 7) {
                    f.add("알고리즘");
                } else if (field.getFieldId() == 8) {
                    f.add("디자이너");
                } else if (field.getFieldId() == 9) {
                    f.add("클라우드 컴퓨팅");
                } else if (field.getFieldId() == 10) {
                    f.add("데이터베이스");
                } else {
                    f.add("보안");
                }
            }
        }
        result.setFields(f.stream().distinct().collect(Collectors.toList()));

        for (InformationQualification informationQualification : result.getInformationQualification()) {
            if (informationQualification.getQualificationId2() == 0) {
                q.add(null);
            } else if (informationQualification.getQualificationId2() == 1) {
                q.add("수도권 내 대학생");
            } else if (informationQualification.getQualificationId2() == 2) {
                q.add("대학생");
            } else if (informationQualification.getQualificationId2() == 3) {
                q.add("직장인");
            } else if (informationQualification.getQualificationId2() == 4) {
                q.add("국민내일배움카드 발급자");
            } else if (informationQualification.getQualificationId2() == 5) {
                q.add("국내 거주자");
            } else if (informationQualification.getQualificationId2() == 6) {
                q.add("실시간 학습 가능자");
            } else if (informationQualification.getQualificationId2() == 7) {
                q.add("대학원생");
            } else if (informationQualification.getQualificationId2() == 8) {
                q.add("졸업생");
            } else {
                q.add("미취업자");
            }
        }

        result.setQualifications(q.stream().distinct().collect(Collectors.toList()));
        return result;
    }

    public List<Information> findClub() {
        List<Information> result = informationRepositoryMapper.findAllClub();
        for (Information information : result) {
            List<String> f = new ArrayList<String>();
            List<String> q = new ArrayList<String>();
            for (RecruitmentField recruitmentField : information.getRecruitmentField()) {
                if (recruitmentField.getFieldId() == 0){
                    f.add(null);
                }
                for (Field field : recruitmentField.getField()) {
                    if (field.getFieldId() == 1) {
                        f.add("프론트엔드");
                    } else if (field.getFieldId() == 2) {
                        f.add("백엔드");
                    } else if (field.getFieldId() == 3) {
                        f.add("ios");
                    } else if (field.getFieldId() == 4) {
                        f.add("안드로이드");
                    } else if (field.getFieldId() == 5) {
                        f.add("인공지능");
                    } else if (field.getFieldId() == 6) {
                        f.add("데이터처리");
                    } else if (field.getFieldId() == 7) {
                        f.add("알고리즘");
                    } else if (field.getFieldId() == 8) {
                        f.add("디자이너");
                    } else if (field.getFieldId() == 9) {
                        f.add("클라우드 컴퓨팅");
                    } else if (field.getFieldId() == 10) {
                        f.add("데이터베이스");
                    } else if(field.getFieldId() == 11) {
                        f.add("보안");
                    }
                }
            }
            information.setFields(f.stream().distinct().collect(Collectors.toList()));

            for (InformationQualification informationQualification : information.getInformationQualification()) {
                if (informationQualification.getQualificationId2() == 1) {
                    q.add("수도권 내 대학생");
                } else if (informationQualification.getQualificationId2() == 2) {
                    q.add("대학생");
                } else if (informationQualification.getQualificationId2() == 3) {
                    q.add("직장인");
                } else if (informationQualification.getQualificationId2() == 4) {
                    q.add("국민내일배움카드 발급자");
                } else if (informationQualification.getQualificationId2() == 5) {
                    q.add("국내 거주자");
                } else if (informationQualification.getQualificationId2() == 6) {
                    q.add("실시간 학습 가능자");
                } else if (informationQualification.getQualificationId2() == 7) {
                    q.add("대학원생");
                } else if (informationQualification.getQualificationId2() == 8) {
                    q.add("졸업생");
                } else if (informationQualification.getQualificationId2() == 9){
                    q.add("미취업자");
                } else {
                    q.add(null);
                }
            }

            information.setQualifications(q.stream().distinct().collect(Collectors.toList()));
        }
        return result;
    }

    public List<Information> findEducation() {
        List<Information> result = informationRepositoryMapper.findAllEducation();
        for (Information information : result) {
            List<String> f = new ArrayList<String>();
            List<String> q = new ArrayList<String>();
            for (RecruitmentField recruitmentField : information.getRecruitmentField()) {
                if (recruitmentField.getFieldId() == 0){
                    f.add(null);
                }
                for (Field field : recruitmentField.getField()) {
                    if (field.getFieldId() == 1) {
                        f.add("프론트엔드");
                    } else if (field.getFieldId() == 2) {
                        f.add("백엔드");
                    } else if (field.getFieldId() == 3) {
                        f.add("ios");
                    } else if (field.getFieldId() == 4) {
                        f.add("안드로이드");
                    } else if (field.getFieldId() == 5) {
                        f.add("인공지능");
                    } else if (field.getFieldId() == 6) {
                        f.add("데이터처리");
                    } else if (field.getFieldId() == 7) {
                        f.add("알고리즘");
                    } else if (field.getFieldId() == 8) {
                        f.add("디자이너");
                    } else if (field.getFieldId() == 9) {
                        f.add("클라우드 컴퓨팅");
                    } else if (field.getFieldId() == 10) {
                        f.add("데이터베이스");
                    } else if(field.getFieldId() == 11) {
                        f.add("보안");
                    }
                }
            }
            information.setFields(f.stream().distinct().collect(Collectors.toList()));

            for (InformationQualification informationQualification : information.getInformationQualification()) {
                if (informationQualification.getQualificationId2() == 1) {
                    q.add("수도권 내 대학생");
                } else if (informationQualification.getQualificationId2() == 2) {
                    q.add("대학생");
                } else if (informationQualification.getQualificationId2() == 3) {
                    q.add("직장인");
                } else if (informationQualification.getQualificationId2() == 4) {
                    q.add("국민내일배움카드 발급자");
                } else if (informationQualification.getQualificationId2() == 5) {
                    q.add("국내 거주자");
                } else if (informationQualification.getQualificationId2() == 6) {
                    q.add("실시간 학습 가능자");
                } else if (informationQualification.getQualificationId2() == 7) {
                    q.add("대학원생");
                } else if (informationQualification.getQualificationId2() == 8) {
                    q.add("졸업생");
                } else if (informationQualification.getQualificationId2() == 9){
                    q.add("미취업자");
                } else {
                    q.add(null);
                }
            }

            information.setQualifications(q.stream().distinct().collect(Collectors.toList()));
        }
        return result;
    }

    public List<Information> findSeminar() {
        List<Information> result = informationRepositoryMapper.findAllSeminar();
        for (Information information : result) {
            List<String> f = new ArrayList<String>();
            List<String> q = new ArrayList<String>();
            for (RecruitmentField recruitmentField : information.getRecruitmentField()) {
                if (recruitmentField.getFieldId() == 0){
                    f.add(null);
                }
                for (Field field : recruitmentField.getField()) {
                    if (field.getFieldId() == 1) {
                        f.add("프론트엔드");
                    } else if (field.getFieldId() == 2) {
                        f.add("백엔드");
                    } else if (field.getFieldId() == 3) {
                        f.add("ios");
                    } else if (field.getFieldId() == 4) {
                        f.add("안드로이드");
                    } else if (field.getFieldId() == 5) {
                        f.add("인공지능");
                    } else if (field.getFieldId() == 6) {
                        f.add("데이터처리");
                    } else if (field.getFieldId() == 7) {
                        f.add("알고리즘");
                    } else if (field.getFieldId() == 8) {
                        f.add("디자이너");
                    } else if (field.getFieldId() == 9) {
                        f.add("클라우드 컴퓨팅");
                    } else if (field.getFieldId() == 10) {
                        f.add("데이터베이스");
                    } else if(field.getFieldId() == 11) {
                        f.add("보안");
                    }
                }
            }
            information.setFields(f.stream().distinct().collect(Collectors.toList()));

            for (InformationQualification informationQualification : information.getInformationQualification()) {
                if (informationQualification.getQualificationId2() == 1) {
                    q.add("수도권 내 대학생");
                } else if (informationQualification.getQualificationId2() == 2) {
                    q.add("대학생");
                } else if (informationQualification.getQualificationId2() == 3) {
                    q.add("직장인");
                } else if (informationQualification.getQualificationId2() == 4) {
                    q.add("국민내일배움카드 발급자");
                } else if (informationQualification.getQualificationId2() == 5) {
                    q.add("국내 거주자");
                } else if (informationQualification.getQualificationId2() == 6) {
                    q.add("실시간 학습 가능자");
                } else if (informationQualification.getQualificationId2() == 7) {
                    q.add("대학원생");
                } else if (informationQualification.getQualificationId2() == 8) {
                    q.add("졸업생");
                } else if (informationQualification.getQualificationId2() == 9){
                    q.add("미취업자");
                } else {
                    q.add(null);
                }
            }

            information.setQualifications(q.stream().distinct().collect(Collectors.toList()));
        }
        return result;
    }

    public List<Information> findCertificate() {
        List<Information> result = informationRepositoryMapper.findAllCertificate();
        for (Information information : result) {
            List<String> f = new ArrayList<String>();
            List<String> q = new ArrayList<String>();
            for (RecruitmentField recruitmentField : information.getRecruitmentField()) {
                if (recruitmentField.getFieldId() == 0){
                    f.add(null);
                }
                for (Field field : recruitmentField.getField()) {
                    if (field.getFieldId() == 1) {
                        f.add("프론트엔드");
                    } else if (field.getFieldId() == 2) {
                        f.add("백엔드");
                    } else if (field.getFieldId() == 3) {
                        f.add("ios");
                    } else if (field.getFieldId() == 4) {
                        f.add("안드로이드");
                    } else if (field.getFieldId() == 5) {
                        f.add("인공지능");
                    } else if (field.getFieldId() == 6) {
                        f.add("데이터처리");
                    } else if (field.getFieldId() == 7) {
                        f.add("알고리즘");
                    } else if (field.getFieldId() == 8) {
                        f.add("디자이너");
                    } else if (field.getFieldId() == 9) {
                        f.add("클라우드 컴퓨팅");
                    } else if (field.getFieldId() == 10) {
                        f.add("데이터베이스");
                    } else if(field.getFieldId() == 11) {
                        f.add("보안");
                    }
                }
            }
            information.setFields(f.stream().distinct().collect(Collectors.toList()));

            for (InformationQualification informationQualification : information.getInformationQualification()) {
                if (informationQualification.getQualificationId2() == 1) {
                    q.add("수도권 내 대학생");
                } else if (informationQualification.getQualificationId2() == 2) {
                    q.add("대학생");
                } else if (informationQualification.getQualificationId2() == 3) {
                    q.add("직장인");
                } else if (informationQualification.getQualificationId2() == 4) {
                    q.add("국민내일배움카드 발급자");
                } else if (informationQualification.getQualificationId2() == 5) {
                    q.add("국내 거주자");
                } else if (informationQualification.getQualificationId2() == 6) {
                    q.add("실시간 학습 가능자");
                } else if (informationQualification.getQualificationId2() == 7) {
                    q.add("대학원생");
                } else if (informationQualification.getQualificationId2() == 8) {
                    q.add("졸업생");
                } else if (informationQualification.getQualificationId2() == 9){
                    q.add("미취업자");
                } else {
                    q.add(null);
                }
            }

            information.setQualifications(q.stream().distinct().collect(Collectors.toList()));
        }
        return result;
    }

    public List<Information> findKdt() {
        List<Information> result = informationRepositoryMapper.findAllKdt();
        for (Information information : result) {
            List<String> f = new ArrayList<String>();
            List<String> q = new ArrayList<String>();
            for (RecruitmentField recruitmentField : information.getRecruitmentField()) {
                if (recruitmentField.getFieldId() == 0){
                    f.add(null);
                }
                for (Field field : recruitmentField.getField()) {
                    if (field.getFieldId() == 1) {
                        f.add("프론트엔드");
                    } else if (field.getFieldId() == 2) {
                        f.add("백엔드");
                    } else if (field.getFieldId() == 3) {
                        f.add("ios");
                    } else if (field.getFieldId() == 4) {
                        f.add("안드로이드");
                    } else if (field.getFieldId() == 5) {
                        f.add("인공지능");
                    } else if (field.getFieldId() == 6) {
                        f.add("데이터처리");
                    } else if (field.getFieldId() == 7) {
                        f.add("알고리즘");
                    } else if (field.getFieldId() == 8) {
                        f.add("디자이너");
                    } else if (field.getFieldId() == 9) {
                        f.add("클라우드 컴퓨팅");
                    } else if (field.getFieldId() == 10) {
                        f.add("데이터베이스");
                    } else if(field.getFieldId() == 11) {
                        f.add("보안");
                    }
                }
            }
            information.setFields(f.stream().distinct().collect(Collectors.toList()));

            for (InformationQualification informationQualification : information.getInformationQualification()) {
                if (informationQualification.getQualificationId2() == 1) {
                    q.add("수도권 내 대학생");
                } else if (informationQualification.getQualificationId2() == 2) {
                    q.add("대학생");
                } else if (informationQualification.getQualificationId2() == 3) {
                    q.add("직장인");
                } else if (informationQualification.getQualificationId2() == 4) {
                    q.add("국민내일배움카드 발급자");
                } else if (informationQualification.getQualificationId2() == 5) {
                    q.add("국내 거주자");
                } else if (informationQualification.getQualificationId2() == 6) {
                    q.add("실시간 학습 가능자");
                } else if (informationQualification.getQualificationId2() == 7) {
                    q.add("대학원생");
                } else if (informationQualification.getQualificationId2() == 8) {
                    q.add("졸업생");
                } else if (informationQualification.getQualificationId2() == 9){
                    q.add("미취업자");
                } else {
                    q.add(null);
                }
            }

            information.setQualifications(q.stream().distinct().collect(Collectors.toList()));
        }
        return result;
    }

    public List<Information> findContest() {
        List<Information> result = informationRepositoryMapper.findAllContest();
        for (Information information : result) {
            List<String> f = new ArrayList<String>();
            List<String> q = new ArrayList<String>();
            for (RecruitmentField recruitmentField : information.getRecruitmentField()) {
                if (recruitmentField.getFieldId() == 0){
                    f.add(null);
                }
                for (Field field : recruitmentField.getField()) {
                    if (field.getFieldId() == 1) {
                        f.add("프론트엔드");
                    } else if (field.getFieldId() == 2) {
                        f.add("백엔드");
                    } else if (field.getFieldId() == 3) {
                        f.add("ios");
                    } else if (field.getFieldId() == 4) {
                        f.add("안드로이드");
                    } else if (field.getFieldId() == 5) {
                        f.add("인공지능");
                    } else if (field.getFieldId() == 6) {
                        f.add("데이터처리");
                    } else if (field.getFieldId() == 7) {
                        f.add("알고리즘");
                    } else if (field.getFieldId() == 8) {
                        f.add("디자이너");
                    } else if (field.getFieldId() == 9) {
                        f.add("클라우드 컴퓨팅");
                    } else if (field.getFieldId() == 10) {
                        f.add("데이터베이스");
                    } else if(field.getFieldId() == 11) {
                        f.add("보안");
                    }
                }
            }
            information.setFields(f.stream().distinct().collect(Collectors.toList()));

            for (InformationQualification informationQualification : information.getInformationQualification()) {
                if (informationQualification.getQualificationId2() == 1) {
                    q.add("수도권 내 대학생");
                } else if (informationQualification.getQualificationId2() == 2) {
                    q.add("대학생");
                } else if (informationQualification.getQualificationId2() == 3) {
                    q.add("직장인");
                } else if (informationQualification.getQualificationId2() == 4) {
                    q.add("국민내일배움카드 발급자");
                } else if (informationQualification.getQualificationId2() == 5) {
                    q.add("국내 거주자");
                } else if (informationQualification.getQualificationId2() == 6) {
                    q.add("실시간 학습 가능자");
                } else if (informationQualification.getQualificationId2() == 7) {
                    q.add("대학원생");
                } else if (informationQualification.getQualificationId2() == 8) {
                    q.add("졸업생");
                } else if (informationQualification.getQualificationId2() == 9){
                    q.add("미취업자");
                } else {
                    q.add(null);
                }
            }

            information.setQualifications(q.stream().distinct().collect(Collectors.toList()));
        }
        return result;
    }

}
