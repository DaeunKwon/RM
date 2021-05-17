
module.exports = {
  runtimeCompiler: true,
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

      }
    }
  },
  configureWebpack: {
    devServer: {
      historyApiFallback: true
    }
  }
}
