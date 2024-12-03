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
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white"> <img src="https://img.shields.io/badge/mongoDB-47A248?style=for-the-badge&logo=MongoDB&logoColor=white"> <img src="https://img.shields.io/badge/redis-FF4438?style=for-the-badge&logo=redis&logoColor=white">

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
    </br>
    <img src="https://github.com/user-attachments/assets/43aab1b0-2881-4ef6-9463-6df79e556c1c" width="500" height="600"/>
    </br>
    </br>
  + 로그인 화면
    </br>
    
    + 사용자는 Google, KAKAO 두 가지 플랫폼으로 소셜 로그인이 가능합니다.
    </br>
    </br>
    <img src="https://github.com/user-attachments/assets/35ef0095-abae-4c7f-bf9a-b9a1abfdbe26" width="800" height="400"/>
    </br>
    </br>
  + 구독 화면
    </br>
    </br>
    <img src="https://github.com/user-attachments/assets/ca4cf137-2fea-4713-b705-e6ee35351737" width="500" height="600"/>
    </br>
    </br>
  + 할당된 서버 화면
    </br>
    
    + 사용자는 해당 페이지에서 동영상 업로드, 다운 서비스를 제공받습니다.
    </br>
    </br>
    <img src="https://github.com/user-attachments/assets/fad13936-da0a-4c5e-98a3-4f7087b048e5" width="800" height="400"/>
    </br>
    </br>
  + 미디어 재생 페이지
    </br>
    
    + 사용자는 해당 페이지에서 Short URL을 통해 동영상 공유 링크 서비스를 제공받습니다.
    </br>
    </br>
    <img src="https://github.com/user-attachments/assets/de93371b-5209-436b-8b74-f6b1bf9e48e2" width="800" height="400"/>
    </br>
    </br>
  + 마이페이지
    </br>
    
    + 해당 페이지에서 사용자의 정보변경이 가능합니다.
    </br>
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
<img src="https://github.com/user-attachments/assets/0d24a701-46d2-427b-93a3-3f2758285010" width="500" height="300"/>
</br>
</br>

+ #### 도커 스웜 아키텍처 1
<img src="https://github.com/user-attachments/assets/a3aef4ac-b9a4-4204-9d5e-c2ab3eaa165c" width="500" height="300"/>
</br>
</br>

+ #### 도커 스웜 아키텍처 2
<img src="https://github.com/user-attachments/assets/432249a9-1ec0-48b8-a84d-e2250656d238" width="500" height="300"/>
</br>
</br>

+ #### 깃허브 액션 플로우
<img src="https://github.com/user-attachments/assets/733d8e76-1788-420a-8ba0-ff027f8507e8" width="500" height="300"/>
</br>
</br>

---

# 설계 및 구현

+ ### MSA
  + 저희 서비스의 구조는 여러 개의 작은 서비스로 구성되어 각 서비스가 독립적으로 개발되고 배포되는 구조인 MSA 아키텍처를 사용하였습니다.외부로터 들어온 요청은  API 게이트웨이를 통해 내부 서비스로 라우팅해줍니다.
  ![게이트웨이](https://github.com/user-attachments/assets/b17fb532-a003-4206-b5fe-5fed91b9af63)

  </br>
  
  + API Gateway로 SCG(Spring Cloud Gatweay)를 사용하였습니다. POC를 만드는 과정에서 각 서비스마다 공통적으로 들어가있는 인증/인가로 인해 서비스내 불필요한 중복요청이 생겼습니다. 이러한 중복요청을 간소화 해주는 기능을 찾던 중 스프링 클라우드 게이트 웨이의 predicate와 filte가 저희 서비스에 적합하다 생각하여 도입하게되었습니다. Gateway를 통해 들어온 클라이언트의 요청이 어떤 서비스로 전달될지 결정하고 해당 토큰이 유효한지, 검증 후 서비스로 요청을 전달합니다.
    
  </br>
  
  + 인증이 필요하지 않은 API 요청의 경우 인증 처리가 필요하지 않도록, 별도의 처리가 필요한 특정 API의 경우는 각 상황에 맞추어 만들어 적용하는 방식으로 구현을 하였습니다.
  ![SCG 흐름](https://github.com/user-attachments/assets/839037ea-55b6-46c1-b5a1-6f81e60ab0e7)

+ ### 인증 흐름
  ![인증흐름](https://github.com/user-attachments/assets/b4e3490d-71c7-453b-ac84-8342a490fea5)
</br>
</br>

  
		
		


