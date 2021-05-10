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

      },
      '/login': {
        target: 'https://localhost:8090',
        changeOrigin: true,
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
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
