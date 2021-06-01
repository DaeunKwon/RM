import Vue from 'vue';
import VueRouter from 'vue-router';
import test from '../views/test.vue';
import test1 from '../views/test1.vue';
import hello from '../components/HelloWorld.vue';
import login from '../components/user/login.vue';
import join from '../components/user/join.vue'
import main from '../views/project/main.vue';
import prjWrite from '../views/project/write.vue';
import rptList from '../views/report/list.vue';
import rptWrite from '../views/report/write.vue';
import prjDetail from '../views/project/detail.vue';
import dailyRpt from '../views/report/daily.vue';
import monthlyRpt from '../views/report/monthly.vue';
import store from '../store';

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/monthlyRpt',
            component: monthlyRpt,
        },
        {
            path: '/dailyRpt',
            component: dailyRpt,
        },
        {
            path: '/prjDetail',
            component: prjDetail,
        },
        {
            path: '/rptWrite',
            component: rptWrite,
        },
        {
            path: '/rptList',
            component: rptList,
        },
        {
            path: '/prjWrite',
            name: 'prjWrite',
            component: prjWrite,
        },
        {
            path: '/main',
            component: main,
            beforeEnter: function (to, from, next) {
                let user
                axios.get('/api/user/info')
                    .then(res => {
                        user = res.data
                        console.log(user)
                        if (!!user['email']) {
                            next()
                        } else {
                            next('/')
                        }
                    })
            },
            props: true
        },
        {
            path: '/',
            component: login,
            beforeEnter: function (to, from, next) {
                let user
                axios.get('/api/user/info')
                    .then(res => {
                        user = res.data
                        console.log(user)
                        if (!!user['email']) {
                            next('/main')
                        } else {
                            next()
                        }
                    })
            },
            props: true
        },
        {
            path: '/join',
            component: join,
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