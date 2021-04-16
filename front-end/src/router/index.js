import Vue from 'vue';
import VueRouter from 'vue-router';
import test from '../views/test.vue';
import test1 from '../views/test1.vue';
import hello from '../components/HelloWorld.vue';
import login from '../components/user/login.vue';
import join from '../components/user/join.vue'

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            component: join,
        },
        {
            path: '/login',
            component: login,
        },

        {
            path: '/hello',
            component: hello,
        },

        {
            //  path : url 주소
            path: '/test',
            // component: url 주소로 갔을 때 표시될 컴포넌트
            component: test,
        },
        {
            path: '/test1',
            component: test1,
        }
    ]
});

export default router;