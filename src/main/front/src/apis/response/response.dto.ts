import {ResponseCode, ResponseMessage} from "../../typs/enums";

export default interface ResponseDto {
    code :ResponseCode; //열거형 타입으로 지정
    message : ResponseMessage;
}