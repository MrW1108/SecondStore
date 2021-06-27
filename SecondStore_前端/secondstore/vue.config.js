module.exports = {
    devServer: {
      proxy: {
        // /api是后台数据接口的上下文路径
        '/api': {
            //这里的地址是后端数据接口的地址
          //  http://bwmgd.club:25510/#/home
            // target: 'http://111.229.37.167/',
            target: 'http://localhost:8084/',
            //允许跨域
            changOrigin: true,
            pathRewrite: {
              '^/api': ''
            }
        }
      }
    },
    productionSourceMap: process.env.NODE_ENV === 'production' ? false : true
  }