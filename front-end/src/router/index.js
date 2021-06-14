import Vue from 'vue';
import VueRouter from 'vue-router';
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

/** 같은 페이지에서 같은 페이지로 $router.push 한 오류 처리 (ex : 홈페이지에서 홈 로고를 클릭한 경우) */
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(() => {
        return window.location.reload()
    })
};

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
            path: '/test1',
            component: test1,
        },
        {
            path: '/testaaaa',
            component: testaaaa,
        },
        {
            path: '/com_detail',
            component: com_detail,
        },
        {
            path: '/com_main',
            component: com_main,
        }
    ]
});

export default router;