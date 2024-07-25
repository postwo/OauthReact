import React, {ChangeEvent, useState} from 'react';
import './App.css';
import InputBox from "./components/inputBox"; //// InputBox 컴포넌트 가져오기

function App() {

  return (
      <>
          <div className='text-link-lg full-width'>회원가입</div>
          <div className='kakao-sign-in-button'></div>
          <div className='naver-sign-in-button'></div>
      </>
  );
}

export default App;
