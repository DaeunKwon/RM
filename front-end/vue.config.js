module.exports = {
  lintOnSave: false,
  transpileDependencies: [
    'vuetify'
  ],
  outputDir: "../src/main/resources/static",
  indexPath: "../static/index.html",
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8090',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  },
  configureWebpack: {
    devServer: {
      historyApiFallback: true
    }
  }
}
