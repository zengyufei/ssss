// 将request处的代码复制到此处，修改了baseURL，能够让mock请求和实际请求同时使用
// create an axios instance
const api = axios.create({
  baseURL: 'http://localhost:8081', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 30000 // request timeout
})

// request interceptor
api.interceptors.request.use(
  config => {
    // do something before request is sent
    config.headers['TOKEN'] = '123'

    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
api.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    const headers = response.headers
    
    // 是否需要返回请求头
    if(response.config && response.config.returnHeaders){
    	return [res, headers];
    }
    
    // 导出EXCEL
    if (
        headers['content-type']==='application/vnd.ms-excel;charset=utf-8'
        || (headers['content-disposition'] && (headers['content-disposition'].indexOf('attachment; filename')===0 || headers['content-disposition'].indexOf('attachment')===0))
    ) {
      if(response.config && response.config.parseFilename){
        let contentDisposition = headers['content-disposition'];
        let arr = contentDisposition.split('=');
        let filename = decodeURI(arr[1]);
        return [res, filename];
      }else{
        return res;
      }
    }

    // 此处改动，不然会报错
    // if the custom code is not 20000, it is judged as an error.
    console.log(res) // for debug
    if (res.code !== 200) {
      alert(res.msg || 'Error')

      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    alert(error.message)
    return Promise.reject(error)
  }
)
