import React,{useEffect} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {useCookies} from "react-cookie";

export default function OAuth() {

    const { token,expirationTime } = useParams();
    const [cookie,setCookie] = useCookies();
    const navigate = useNavigate();

    useEffect(() => {
        console.log(token+" ====="+expirationTime);
        if (!token || !expirationTime) return ;

        const now = (new Date().getTime()) * 1000; //밀리세컨드로 만들어줘야 하기 때문에 1000을 곱해준다
        const expires = new Date(now + Number(expirationTime) ); //expirationTime이 문자열이어서 number를 사용해 숫자로 변경

        setCookie('accessToken',token,{expires, path:'/'});
        navigate('/');

    }, [token]);


    return (
        <></>
    )
}