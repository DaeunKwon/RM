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
import testaaaa from '../views/commute/testaaaa.vue';
import com_detail from '../views/commute/com_detail.vue';
import com_main from '../views/commute/com_main.vue';
import dailyRpt from '../views/report/daily.vue';

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    routes: [
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
            component: prjWrite,
        },
        {
            path: '/main',
            component: main,
        },
        {
            path: '/',
            component: login,
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