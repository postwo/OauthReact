import {ResponseCode, ResponseMessage} from "../../typs/enums";

export default interface ResponseDto {
    code :ResponseCode; //열거형 타입으로 지정 == 백에있는거 ==common에 있다
    message : ResponseMessage;
}