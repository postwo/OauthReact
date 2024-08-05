import './App.css';
import {Route, Routes} from "react-router-dom";
import SignUp from "./views/Authentication/SignUp"; // InputBox 컴포넌트 가져오기

function App() {

  return (
      <Routes>
          <Route path='/auth'> {/*첫 번째 위치이므로 절대값을 넣는다 두번째 부터는 넣으면 안된다*/}
           <Route path='sign-up' element={<SignUp/>} />
          </Route>
      </Routes>
  );
}

export default App;
