REACT 생성
# front=프로젝트 이름이다 ,option = --template => typescript를 사용한다는 뜻이다
npx create-react-app front --template typescript

# 세 가지 패키지인 react-router-dom, react-cookie, axios를 설치하는 것을 의미
npm i react-router-dom react-cookie axios

# 삭제 = 밑부분들 다삭제 하기
App.test.tsx,
setupTests.ts,
reportWebVitals.ts

index.tsx 에서

import reportWebVitals from './reportWebVitals';

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();



# rfc
이거는 vs에서 다운 가능  =>   VS Code ES7+ React/Redux/React-Native/JS snippets


import React from 'react'

export default function ClassComponent() {
return (
<div>ClassComponent</div>
)
}

# react 실행
npm run start

# JWT 11.2 사용


# requestdto
클라이언트가 서버에 요청을 보낼 때 필요한 데이터

# responsedto
서버가 클라이언트에 응답할 때 필요한 데이터와 정보

# 구글 gmail 
앱 비밀번호 조회해서 들어가기

# ts 에서 주석 달면 에러가 날수도 있다 기억해두기

#  에러
threw exception with message: Unknown provider ID 'kakao' 이거는 rpovider를 설정안했든가 아니면 들여쓰기를 잘못했을 가능성이 크다 

# oauth 테스트 주소
http://localhost:4040/oauth2/authorization/{kakao,naver}