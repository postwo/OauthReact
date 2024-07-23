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
이거는 vs에서 다움 가능  =>   VS Code ES7+ React/Redux/React-Native/JS snippets


import React from 'react'

export default function ClassComponent() {
return (
<div>ClassComponent</div>
)
}

# react 실행
npm run start

서타몽3부터 듣기 