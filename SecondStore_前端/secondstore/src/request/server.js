import axios from "axios";

let server = axios.create({
    baseURL: "/api",
    timeout: "5000"
})

//请求拦截
// server.interceptors.request.use(config => {})


//响应拦截
// server.interceptors.response.use(config =>{})


export default server;
