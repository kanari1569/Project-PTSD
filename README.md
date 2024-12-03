# 카카오 클라우드스쿨 최종 프로젝트 3조  
## PTSD.ly
팀원: 이주석, 김연수, 이상현, 이재익, 황석준

#### 기간/인원
     기간 : 23.08.31 ~ 23.11.09
     인원 : 5명
---
# 프로젝트 개요

+ ## 프로젝트명
     구독형 개인 미디어 서버 제공 플랫폼과 단축 URL 서비스를 통한 영상 공유
+ ## 기획 배경
     영상을 저장하고, 친구들과 공유할 수 있는 개인 서버를 갖고 싶지만 서버를 구축하는데 어려움이 있는 사용자를 대상으로 미디어 서버를 제공해주는 플랫폼을 만들기로 결정
+ ## 수행 목표
     사용자로부터 구독 요청이 들어오면 AWS로부터 EC2와 S3를 생성하여 미디어 서버를 생성해줍니다. 미디어 서버는 영상 업로드와 스트리밍 기능 및 동영상 공유 서비스를 제공합니다.

---

# 개발 환경
  
###  BACKEND
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring cloud gateway-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> </br> 
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> ![jwt 기술스택 이미지](https://github.com/user-attachments/assets/3ccca7b4-3925-4272-b4ed-dfce1fc8826e) <img src="https://img.shields.io/badge/Oauth-EB5424?style=for-the-badge&logo=auth0&logoColor=white">

###  DATABASE
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white"> <img src="https://img.shields.io/badge/mongoDB-47A248?style=for-the-badge&logo=MongoDB&logoColor=white">

### FRONTEND
<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <img src="https://img.shields.io/badge/redux-764ABC?style=for-the-badge&logo=redux&logoColor=black"> <img src="https://img.shields.io/badge/redux persist-764ABC?style=for-the-badge&logo=redux&logoColor=black"> </br>
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">

### INFRA
<img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=amazonwebservices&logoColor=white"> <img src="https://img.shields.io/badge/aws s3-569A31?style=for-the-badge&logo=amazons3&logoColor=white"> <img src="https://img.shields.io/badge/EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> <img src="https://img.shields.io/badge/GitAction-181717?style=for-the-badge&logo=github&logoColor=white"> </br>
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/docker swarm-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/docker hub-2496ED?style=for-the-badge&logo=docker&logoColor=white">

### IDE
<img src="https://img.shields.io/badge/Intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/Visual Studio-5C2D91?style=for-the-badge&logo==Visual Studio&logoColor=white"/>

### TOOL
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white">

---
# 화면 구성

  + 메인 화면
    </br>
    <img src="https://github.com/user-attachments/assets/43aab1b0-2881-4ef6-9463-6df79e556c1c" width="500" height="600"/>
    </br>
    </br>
  + 로그인 화면
    + 사용자는 Google, KAKAO 두 가지 플랫폼으로 소셜 로그인이 가능합니다.
    </br>
    <img src="https://github.com/user-attachments/assets/35ef0095-abae-4c7f-bf9a-b9a1abfdbe26" width="800" height="400"/>
    </br>
    </br>
  + 구독 화면
    </br>
    <img src="https://github.com/user-attachments/assets/ca4cf137-2fea-4713-b705-e6ee35351737" width="500" height="600"/>
    </br>
    </br>
  + 할당된 서버 화면
    + 사용자는 해당 페이지에서 동영상 업로드, 다운 서비스를 제공받습니다.
    </br>
    <img src="https://github.com/user-attachments/assets/fad13936-da0a-4c5e-98a3-4f7087b048e5" width="800" height="400"/>
    </br>
    </br>
  + 미디어 재생 페이지
    + 사용자는 해당 페이지에서 Short URL을 통해 동영상 공유 링크 서비스를 제공받습니다.
    </br>
    <img src="https://github.com/user-attachments/assets/de93371b-5209-436b-8b74-f6b1bf9e48e2" width="800" height="400"/>
    </br>
    </br>
  + 마이페이지
    + 해당 페이지에서 사용자의 정보변경이 가능합니다.
    </br>
    <img src="https://github.com/user-attachments/assets/a3083247-f858-4d2b-ba18-28dfaeb321c5" width="800" height="400"/>
    </br>
    </br>
---

# DATABASE 

### 미디어 DB

+ #### 미디어 DB ERD 1
![미디어 서버 DB 1](https://github.com/user-attachments/assets/c4d8dea8-b6f2-47b6-94a2-a1e19a4aee3c)

+ #### 미디어 DB ERD 2
![미디어 서버 DB 2](https://github.com/user-attachments/assets/32240ce5-60c4-4c63-a7d5-f825a597b2f3)

+ #### 미디어 DB ERD 3
![미디어 서버 DB 3](https://github.com/user-attachments/assets/874f2011-44db-445d-bcea-b63e58f26710)

+ #### 미디어 DB ERD 4
![미디어 서버 DB 4](https://github.com/user-attachments/assets/04d3bf21-67ba-4075-a3d0-b0a591cf0603)

+ #### 미디어 DB ERD 5
![미디어 서버 DB 5](https://github.com/user-attachments/assets/693ed63a-0c85-45ca-b346-9e4ef98c028b)

---

### 사용자 DB

+ #### 사용자 DB ERD
![사용자 DB](https://github.com/user-attachments/assets/6ea57bca-1b7e-4a92-8775-9050bec759e1)

---

### AIM DB (Amazon Instacne Manager)

+ #### AIM DB ERD
![AIM DB](https://github.com/user-attachments/assets/ab8278e0-bc25-43a7-b8fa-8fc2395c290d)

---

### URL DB

+ #### URL DB ERD
![URL DB](https://github.com/user-attachments/assets/d91e462c-6913-476f-b56f-e31cc9e7e0e9)

---

# 시스템 아키텍처


		
		


