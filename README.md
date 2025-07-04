# 카카오 클라우드스쿨 최종 프로젝트 3조  
## PTSD.ly
팀원: 이주석, 김연수, 이상현, 이재익, 황석준

#### 기간/인원
     기간 : 23.08.31 ~ 23.11.09
     인원 : 5명
#### 맡은 역할
     소셜 로그인 / 회원가입, 인증 구현
     API Gateway 구현
     Infra 구축
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
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring cloud gateway-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> </br> 
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

### ERD
![ERD 전체](https://github.com/user-attachments/assets/ed3e1d2a-6af7-4629-bddd-c20c19dde29e)

<!--
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



### 사용자 DB

+ #### 사용자 DB ERD
![사용자 DB](https://github.com/user-attachments/assets/6ea57bca-1b7e-4a92-8775-9050bec759e1)



### AIM DB (Amazon Instacne Manager)

+ #### AIM DB ERD
![AIM DB](https://github.com/user-attachments/assets/ab8278e0-bc25-43a7-b8fa-8fc2395c290d)



### URL DB

+ #### URL DB ERD
![URL DB](https://github.com/user-attachments/assets/d91e462c-6913-476f-b56f-e31cc9e7e0e9)

-->
---

# 서비스 아키텍처
<img src="https://github.com/user-attachments/assets/0d24a701-46d2-427b-93a3-3f2758285010" width="500" height="300"/>
</br>
</br>

---

# 설계 및 구현

+ ### MSA
  + 저희 서비스의 구조는 여러 개의 작은 서비스로 구성되어 각 서비스가 독립적으로 개발되고 배포되는 구조인 MSA 아키텍처를 사용하였습니다.외부로터 들어온 요청은  API 게이트웨이를 통해 내부 서비스로 라우팅해줍니다.
  ![게이트웨이](https://github.com/user-attachments/assets/b17fb532-a003-4206-b5fe-5fed91b9af63)

  </br>
  
  + API Gateway로 SCG(Spring Cloud Gatweay)를 사용하였습니다. Gateway를 통해 들어온 클라이언트의 요청이 어떤 서비스로 전달될지 결정하고 해당 토큰이 유효한지, 검증 후 서비스로 요청을 전달합니다.
    
  </br>
  
  + 인증이 필요하지 않은 API 요청의 경우 인증 처리가 필요하지 않도록, 별도의 처리가 필요한 특정 API의 경우는 각 상황에 맞추어 만들어 적용하는 방식으로 구현을 하였습니다.
  ![SCG 흐름](https://github.com/user-attachments/assets/839037ea-55b6-46c1-b5a1-6f81e60ab0e7)

+ ### 인증 흐름
  ![인증흐름](https://github.com/user-attachments/assets/b4e3490d-71c7-453b-ac84-8342a490fea5)
</br>
</br>

+ ### Docker Swarm 아키텍처
  
  </br>
  
  + #### 아키텍처 1
    + AWS의 EC2를 서버로 사용하고있으며 각 인스턴스들은 Docker swarm network로 연결되어 통신되고있는 구조입니다. docker swarm은 여러개의 ec2 인스턴스를 클러스터로 형성하여 컨테이너를 배포 및 스케일링 할 수 있도록해줍니다.
    + EC2 Instance들은 오토 스케일링 그룹에 포함되어 평균 CPU 사용률에 따라 스케일 아웃됩니다. 외부로 부터 들어오는 요청은 ELB를 통해 내부 Ec2 Instance에 로드 밸런싱됩니다.
  
  ![도커스웜1](https://github.com/user-attachments/assets/a3aef4ac-b9a4-4204-9d5e-c2ab3eaa165c)
  
  </br>

  + #### 아키텍처 2
    + Ec2 Instance들은 Worker Node로 사용이되는데 이를 관리하기 위해 별도의 Manager Node를 두었습니다.
    +  Mnager Node와 Worker node들은 docker swarm netwrk로 연결되어있고 각 서비스들이 배포되어있습니다.
    + worker node가 스케일 아웃되면 해당 node에 서비스를 배포해줘야하는데 해당 기능을 수행하기 위해 node manager service를 도입하였습니다.
  
  ![도커스웜2](https://github.com/user-attachments/assets/432249a9-1ec0-48b8-a84d-e2250656d238)
  
  </br>

+ ### 무중단 배포
  + 서비스들의 버전관리가 이루어져야 하는데 일일히 수동으로 하기에는 어려움이 있다고 생각하여 node 내부에서 동작하고 있는 서비스의 버전을 무중단 배포하기 위해 rolling update 기능을 추가했습니다.
 
  </br>

  ![깃액션플로우](https://github.com/user-attachments/assets/733d8e76-1788-420a-8ba0-ff027f8507e8)

---

# 회고 및 마무리

+ 카카오 클라우드 스쿨에서 지원하는 krampoline을 단축 URL 서비스에 활용하고자 하였으나, 크램폴린을 활용해 배포하지 못해 아쉬움이 남는다. 그래서 기회가 된다면 쿠버네티스를 활용해 서비스를 구축해보고 싶다. 또한 현재 단축 URL 서비스의 DB는 Document형태로 저장하는 Mongodb를 사용하고 있는데, DB또한 Document형식이 아닌 key-value 형태의 DB를 활용해 DB 서버를 구축하고 싶다.

</br>

+ AWS에서 제공하는 다양한 서비스를 사용해 볼 수 있는 좋은 시간이였고, 개인적 성장과 함께 협업능력을 기를 수 있었다. 공유자원 사용, 보안, 디자인 등 기술적으로 보완해야할 부분이 아직 남아있는 프로젝트 이기 때문에 기회가 된다면 리팩토링을 통해 개선해 나가고 싶다.

</br>

+ 웹 페이지 서비스 개발을 맡으면서 UX 디자인을 생각을 디테일하게 잡으면서 개발을 못한게 아쉬웠고, 페이지도 서비스에 맞게 디자인 하거나 반응형 페이지를 제작 못한게 아쉬워 앞으로는 UX/UI를 고려하면서 제작해야겠다는 생각이 들었고, 리액트라는 프레임워크를 사용하는데에 있어 리덕스로 상태관리를 못한것도 한편으로 아쉬웠다. 이번 프로젝트를 회고하면서 코드를 리팩토링하여 재사용하거나 세분화를 하는 과정으로 단점을 보완해보고 싶다.


  
		
		


