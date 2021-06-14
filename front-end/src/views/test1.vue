<template>
  <div id="app">
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr v-for="item in getweekdata" :key="item.name">
            <th class="text-left">{{ item.Sun }} (일)</th>
            <th class="text-left">{{ item.Mon }} (월)</th>
            <th class="text-left">{{ item.Tue }} (화)</th>
            <th class="text-left">{{ item.Wed }} (수)</th>
            <th class="text-left">{{ item.Tur }} (목)</th>
            <th class="text-left">{{ item.Fri }} (금)</th>
            <th class="text-left">{{ item.Sat }} (토)</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td v-for="item in weeklist" :key="item.name">
              {{ Math.floor(item.com_total / 60) }} 시간
              {{ item.com_total % 60 }} 분
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>
 <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

<script>
export default {
  data: () => ({
    week: [],
    weeklist: [],
  }),
};
</script>

<style>
</style>


    

  

 
