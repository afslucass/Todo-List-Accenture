<template>
  <div>
    <div id='create-list-container'>
      <create-list v-on:criatedListName='createDbList($event)'/>
    </div>
    <TodoList v-for='(lista) in listas' :key='lista.id' :lista='lista' :lista_id='lista.id'></TodoList>
  </div>
</template>

<script>
import CreateList from './components/CreateList.vue'
import TodoList from './components/TodoList.vue'

export default {
  name: 'App',
  components: {
    TodoList,
    CreateList
  },
  data() {
    return {
      listas: []
    }
  },
  async created() {
    let response = await fetch('http://localhost:8080/find/lista/', {
        method: 'POST'
      })
      this.listas = await response.json()
      console.log(this.listas)
  },
  methods: {
    async createDbList(event) {
      if(event == '') {
        alert('Give a Name to the List')
        return
      }
      let response = await fetch(`http://localhost:8080/create/lista/${event}`, {
        method: 'POST'
      })
      let json = await response.json()
      this.listas.push(json)
    }
  }
}
</script>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');

  html, body {
    padding: 0px;
    margin: 0px;
    box-sizing: border-box;
    font-family: 'Roboto', sans-serif;

    background-color: #F2F2F2;
  }

  #create-list-container {
    padding-left: 50px;
    margin-bottom: 50px;

    box-sizing: border-box;
    
    background-color: #333333;
    color: #F2F2F2;
    
    height: 50px;
    width: 100%;

    display: flex;
    flex-flow: column wrap;
    justify-content: center;
  }
</style>
