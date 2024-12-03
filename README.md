# 카카오 클라우드스쿨 최종 프로젝트 3조  
## PTSD.ly
팀원: 이주석, 김연수, 이상현, 이재익, 황석준

#### 기간/인원
     기간 : 23.08.31 ~ 23.11.09
     인원 : 5명
---

## 1.기획 배경
영상을 저장하고, 친구들과 공유할 수 있는 개인 서버를 갖고 싶지만 서버를 구축하는데 어려움이 있는 사용자를 대상으로 미디어 서버를 제공해주는 플랫폼을 만들기로 결정

##  BACKEND
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> ![jwt 기술스택 이미지](https://github.com/user-attachments/assets/3ccca7b4-3925-4272-b4ed-dfce1fc8826e)

##  DATABASE
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">

## FRONTEND
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">

## INFRA
<img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=amazonwebservices&logoColor=white"> <img src="https://img.shields.io/badge/EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> <img src="https://img.shields.io/badge/GitAction-181717?style=for-the-badge&logo=github&logoColor=white"> 

## IDE
<img src="https://img.shields.io/badge/Intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">

## TOOL
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/gitkraken-179287?style=for-the-badge&logo=gitkraken&logoColor=white"> <img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white">

---
## 1. 기획배경
+ 카카오 클라우드 스쿨에 등교하며 점심 식사와 함께할 인원에 대한 고민을 하는 클래스 메이트들의 모습을 짧지않은 시간 보아왔습니다. 클래스 메이트들에게 보다 편리하게 점심 식사 스케쥴을 세울 기회를 제공함과 동시에, 미니 프로젝트지만 실사용 가치가 있는 웹 서비스를 구현하고자 하는 희망에 “오늘 점심 뭐먹지?” → 오점뭐? 를 기획하게 되었습니다.

  
+ 본 프로젝트는 2주라는 짧은 개발 기간 동안 협업으로 이루어지는 프로젝트로, 프로젝트 규모를 작게 해 기획부터 개발, 운영, 그리고 유지 보수까지 일반적인 프로젝트에서 이루어지는 과정을 모두 경험하는 것을 목표로 하고 있습니다.

<img src="https://github.com/user-attachments/assets/6a03b052-0aba-481c-9343-906fe0ef4399" align="right">



## 2. UI

+ 로그인 화면

  <img src="https://github.com/user-attachments/assets/8818b99e-2b74-4e59-a7f2-c7cc4f22c394" width="800" height="400"/>

+ 회원가입 화면

  <img src="https://github.com/user-attachments/assets/ce1386f9-bc35-4766-815a-13b77ad39f76" width="800" height="400"/>


+ 메인 화면

  <img src="https://github.com/user-attachments/assets/77ad41c5-ff67-4465-a1d6-84e42b47c204" width="800" height="400"/>


+ 글 작성 화면
  + 인원수, 배달 여부, 음식 종류를 선택하여 게시글을 작성할 수 있습니다.
  </br>
  <img src="https://github.com/user-attachments/assets/a8ca64d6-0404-41f6-86a4-06fd9f58a0ad" width="400" height="600"/>


+ 게시글 보기 화면
  + 작성자 이외의 회원이 글을 볼 경우 참여 아이콘이 활성화됩니다.
  </br>
  <img src="https://github.com/user-attachments/assets/1a8b8316-e6aa-42c0-8f7c-bd42d1958b6f" width="400" height="600"/>


+ 참여 아이콘 활성화 화면
  + 활성화된 참여 아이콘을 클릭하면 사용자의 아이디가 목록에 추가됩니다.
  </br>
   <img src="https://github.com/user-attachments/assets/e98df3cb-a331-465f-ac66-f4054eee8cca" width="400" height="600"/>


## 시스템 아키텍처
![시스템 아키텍처 JPG](https://github.com/user-attachments/assets/3a6fc9b5-bce6-43c4-a3e5-e7bc9688b1df)

## 업무 분장
#### FRONTEND
     김태민, 최현지 - 각종 페이지 UI 설계 및 구현
#### 회원, 로그인
     김준연, 이상현 - 회원 정보관리, JWT 토큰을 활용한 인증 및 예외처리 기능 구현
#### 게시글 CRUD
     김연수, 김종민 - 기본적인 게시글의 CRUD, 게시글 끌올, 참여 기능 구현
#### 배포
     김연수, 이상현 - GitAction을 사용한 무중단 배포 기능 구현
		
		


