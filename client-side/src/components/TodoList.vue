<template>
  <div class="content-container">
    <div class="list-container">
      <div>{{ lista.name }}</div>
      <div>
        <form v-on:submit.prevent>
          <label>Create New Item: </label>
          <input type="text" v-model='nameItem'>
          <input type="submit" id='button-list' v-on:click='createNewItem()' value="Create">
        </form>
      </div>
    </div>
    <div>
      <item v-for='(item, index) in lista.items' :key='item.id' :item='item' :index_item='index' :lista_id='lista_id' :item_id='item.id' v-on:removerItem='lista.items.splice($event, 1)' />
    </div>
  </div>
</template>

<script>
import Item from './Item.vue'

export default {
  name: 'TodoList',
  props: {
    lista: Object,
    lista_id: Number
  },
  data() {
    return {
      nameItem: ''
    }
  },
  methods: {
    async createNewItem(){
      let response = await fetch('http://localhost:8080/create/item', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ "lista_id": this.lista.id, "tarefa": this.nameItem  })
      })
      if(this.lista.items == undefined) this.lista.items = []
      let json = await response.json()
      this.lista.items.push(json)
    }
  },
  components: {
    Item
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .list-container {
    height: 50px;
    width: 500px;

    padding-left: 50px;

    display: flex;
    flex-flow: row wrap;
    justify-content: space-between;
    align-items: center;

    background-color: #333333;
    color: #F2F2F2;

    border-radius: 5px;
  }

  #button-list {
    margin-right: 20px;
  }

  .content-container {
    width: 500px;
    margin-left: 50px;
  }

  .list-form {
    display: flex;
    flex-flow: wrap row;
  }
</style>
