<template>
    <div>
        <form v-on:submit.prevent="$emit('criatedListName', name)">
            <label for="nameList">Create New List: </label>
            <input type="text" name='nameList' v-model='name'>
            <input type="submit" value="Create">
        </form>
    </div>
</template>

<script>
export default {
    name: 'create-list',
    data() {
        return {
            name: ''
        }
    }
}
</script>

<style scoped>

</style>