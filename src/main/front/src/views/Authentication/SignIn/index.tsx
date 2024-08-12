import React, {ChangeEvent, KeyboardEvent, useRef, useState} from 'react';
import InputBox from "../../../components/inputBox";
import './style.css';
import {useNavigate} from "react-router-dom";
import {SignInRequestDto} from "../../../apis/requset/auth";
import {signInRequest, SNS_SIGN_IN_URL} from "../../../apis";
import {SignInResponseDto, SignUpResponseDto} from "../../../apis/response/auth";
import {ResponseCode} from "../../../typs/enums";
import {ResponseBody} from "../../../typs";
import {useCookies} from "react-cookie";


export default function SignIn() {

    const idRef = useRef<HTMLInputElement | null> (null);
    const passwordRef = useRef<HTMLInputElement | null> (null);

    const [cookie,setCookie] = useCookies();// 쿠키생성

    const [id,setId] = useState<string>('');
    const [password,setPassword] = useState<string>('');

    const [Message, setMessage] = useState<string>('');

    const navigate = useNavigate();

    const signInResponse = (responseBody : ResponseBody<SignUpResponseDto>) =>{
        if (!responseBody) return;

        const {code} = responseBody;
        if (code === ResponseCode.VALIDATION_FAIL) alert('아이디와 비밀번호를 입력하세요');
        if (code === ResponseCode.SIGN_IN_FAIL) setMessage('로그인 정보가 일치하지 않습니다.');
        if (code === ResponseCode.DATABASE_ERROR) alert('데이터베이스 오류입니다');
        if (code !== ResponseCode.SUCCESS) return;

        const {token,expirationTime} =responseBody as SignInResponseDto; //토큰하고 만료시간을 가지고 올려면 강제로 as를 사용해서 변형시켜 줘야 한다

        const now = (new Date().getTime()) * 1000; //밀리세컨드로 만들어줘야 하기 때문에 1000을 곱해준다
        const expires = new Date(now+ expirationTime );

        setCookie('accessToken',token,{expires, path:'/'});
        navigate('/');
    };


    const onIdChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        setId(value);
        setMessage('');
    };
    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        setPassword(value);
    };

    const onSignUpButtonClickHandler = () => {
        navigate('/auth/sign-up') //버튼 클릭시 url 변경

    };
    const onSignInButtonClickHandler = () => {

        if (!id || !password){ //둘다 존재하지 않을경우
            alert('아이디와 비밀번호 모두 입력하세요');
            return;
        }

        const requestBody: SignInRequestDto = {id,password};
        signInRequest(requestBody).then(signInResponse);

    };


    const onSnsSignInButtonClickHandler = (type:'kakao'| 'naver') => {
      window.location.href = SNS_SIGN_IN_URL(type);
    };



    const onIdKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
        if (event.key !== 'Enter') return ;
        if (!passwordRef.current) return;
        passwordRef.current.focus();
    };
    const onPasswordKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
        if (event.key !== 'Enter') return ;
        onSignInButtonClickHandler();
    };



    return (
        <div id='sign-in-wrapper'>
            <div className='sign-in-image'> </div>
            <div className='sign-in-container'>
                <div className='sign-in-box'>
                    <div className='sign-in-title'>{'임대주택 가격 서비스'}</div>
                    <div className='sign-in-content-box'>
                        <div className='sign-in-content-input-box'>
                            <InputBox ref={idRef} title='아이디' placeholder='아이디를 입력해주세요' type='text' value={id}
                                      onChange={onIdChangeHandler}
                                      onKeydown={onIdKeyDownHandler}/>
                            <InputBox ref={passwordRef} title='비밀번호' placeholder='비밀번호를 입력해주세요' type='password'
                                      value={password} onChange={onPasswordChangeHandler}
                                      isErrorMessage message={Message}/>
                        </div>

                        <div className='sign-in-content-button-box'>
                            <div className='primary-button-lg full-width'
                                 onClick={onSignInButtonClickHandler}>{'로그인'}</div>
                            <div className='text-link-lg full-width' onClick={onSignUpButtonClickHandler}>{'회원가입'}</div>
                        </div>

                        <div className='sign-in-content-divider'></div>

                        <div className='sign-in-content-sns-sign-in-box'>
                            <div className='sign-in-content-sign-in-title'>{'SNS 로그인'}</div>
                            <div className='sign-in-content-sns-sign-in-button-box'>
                                <div className='kakao-sign-in-button' onClick={()=>onSnsSignInButtonClickHandler('kakao')}></div>
                                <div className='naver-sign-in-button' onClick={()=>onSnsSignInButtonClickHandler('naver')}></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
