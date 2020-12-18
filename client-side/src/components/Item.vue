<template>
  <div class="item-container">
    <div class="check-text-container">
      <input :id='input_index' type="checkbox" v-model="checked" v-on:change="concluido()">
      <div :id='div_index' class="tarefa">{{ item.tarefa }}</div>
    </div>
    <button class='button-item' v-on:click="removerItem()" :id='button_index'>Remover</button>
  </div>
</template>

<script>
export default {
  name: 'Item',
  data() {
    return {
      input_index: 'input_l' + this.lista_id + 'i' + this.item_id,
      div_index: 'div_l' + this.lista_id + 'i' + this.item_id,
      button_index: 'button_l' + this.lista_id + 'i' + this.item_id,
      checked: false
    }
  },
  mounted() {
    if(this.item.concluida == 1) {
      this.checked = true
      document.querySelector('#div_l' + this.lista_id + 'i' + this.item_id).style.color = 'rgb(0, 0, 0, 0.5)'
      document.querySelector('#input_l' + this.lista_id + 'i' + this.item_id).disabled = true
      document.querySelector('#button_l' + this.lista_id + 'i' + this.item_id).disabled = true
    }
  },
  methods: {
    async concluido() {
      let flag
      if (this.checked) { flag = 1 } else { flag = 0 }
      await fetch('http://localhost:8080/update/item', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ "id": this.item.id, "concluida": flag })
      })

      if(this.checked) {
        document.querySelector('#div_l' + this.lista_id + 'i' + this.item_id).style.color = 'rgb(0, 0, 0, 0.5)'
        document.querySelector('#input_l' + this.lista_id + 'i' + this.item_id).disabled = true
        document.querySelector('#button_l' + this.lista_id + 'i' + this.item_id).disabled = true
      }
      
    },
    async removerItem() {
      await fetch('http://localhost:8080/delete/item', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ "id": this.item.id })
      })
      this.$emit('removerItem', this.index_item)
    }
  },
  props: {
    item: Object,
    lista_id: Number,
    item_id: Number,
    index_item: Number
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .item-container {
    min-height: 50px;
    width: 100%;
    background-color: #E5E5E5;

    display: flex;
    flex-flow: row wrap;
    justify-content: space-between;
    align-items: center;

    margin-left: 50px;

    margin-bottom: 5px;
    margin-top: 5px;

    border-radius: 5px;
  }

  .tarefa {
    width: 400px;
  }

  .check-text-container {
    display: flex;
  }
</style>
