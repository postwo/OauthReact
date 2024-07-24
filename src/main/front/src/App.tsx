import React, {ChangeEvent, useState} from 'react';
import './App.css';
import InputBox from "./components/inputBox"; //// InputBox 컴포넌트 가져오기

function App() {

    const [id, setId] = useState<string>(''); // String을 string으로 변경

    const onIdChangeHandler = (event: ChangeEvent<HTMLInputElement>) =>{
    const {value} = event.target;
        setId(value);
    }

  return (
   <>
    <InputBox title='아이디' placeholder='아이디를 입력해주세요' type='text' value={id} onChange={onIdChangeHandler} />
   </>
  );
}

export default App;
