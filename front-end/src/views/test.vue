<template>
    <div id="clock">
        <p class="date">
            {{ date }}
        </p>
        <p class="time">
            {{ time }}
        </p>
    </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/vue@2.5.2/dist/vue.js"></script>
<script src="{{ asset('js/app.js') }}" async defer></script>
<script>
export default {
		
		data() {
			return {
				time : '',
                date : '',
			};
		},

        mounted(){
            this.getNow();
        },

        methods: { 
            
             getNow() {
                 const timer = setInterval(() => {
                    const today = new Date();
                    this.date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
                    this.time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds(); 
                 },1000);
             }

        }
        
	}
            


</script>

<style>
#clock {
  width:80%; margin:0 auto; height:300px; outline:3px solid orange; padding:10px;
}
</style>