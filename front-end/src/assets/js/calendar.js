  const methods={
    data: () => ({
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      
    }),
  }
  
  
  export default {
    install(Vue){
      Vue.prototype.$data = methods.$data
    }
  }
  