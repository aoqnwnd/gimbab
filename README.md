![ezgif com-gif-maker](https://user-images.githubusercontent.com/71866565/110090317-7d180200-7dda-11eb-8513-64d0ece6f84a.gif)
![image](https://user-images.githubusercontent.com/71866565/109914765-01438a00-7cf4-11eb-8973-f0cd1adb8c9a.png)

## 김선생님들

김밥집 POS기 프로그램
- Oracle
- Eclipse

## 개요
- JDBC를 이용하여 프로그램과 데이터 연동
- Oracle Database 활용
- Swing을 통한 화면 구현

## 구현기능
- 로그인
- 메뉴 주문(단일, 다중 주문), 토핑과 음료 추가 후 결제 기능
- 토핑 재고 관리 및 주문 기능 ( 주문시 sql로 단순 숫자 증가 )
- 판매 관리 기능( 메뉴별 판매량, 판매리스트, 총 매출 )

## 설계중점
- 재고 및 판매 관리를 Database 연동으로 관리하기
- 직관적이고 사용하기 쉬운 UI/UX

## 차후 업데이트 및 보완점
- 아이디, 비밀번호 찾기 
- 토핑 누르고 없음 누른 후 해당 토핑을 다시 누르게 되면 +1된 상태로 차감됨 개선 필요
- 토핑 1개 추가 후 취소 시 돈 삭감 안됨 (선택취소 버튼 눌러야 전체 리셋) 개선 필요
- 판매 리스트 초기화, 삭제 화면 내에선 불가능 개선 필요

## Development environment

| 종류 | 이름 |
| ------ | ------ |
| 개발 툴 | Eclipse, sql developer |
| 버전 관리 | x |
| 데이터베이스 | Oracle |
| 언어 | Java, Sql |
