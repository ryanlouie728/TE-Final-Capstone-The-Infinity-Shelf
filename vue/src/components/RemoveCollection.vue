<template>
    <div class="remove-collection">
        <app-button v-on:click="confirming = true" buttonText="Delete" />
        <confirm 
            v-if="confirming"
            @cancel="confirming = false"
            :message="'Are you sure you want to permanently delete this collection?'"
            :function="removeCollection"
            :arguments="[]"
        />
    </div>
</template>

<script>
import CollectionService from '../services/CollectionService';
import AppButton from './Button.vue';
import Confirm from './Confirm.vue'

export default {
    name: 'remove-collection',
    components: { AppButton, Confirm },
    props: ['collection'],
    data() {
        return {
            confirming: false
        }
    },
    methods: {
        removeCollection(){
            CollectionService.deleteCollection(this.collection.collectionId).then((response) => {
                if(response.status == 200){
                    this.$router.push({name: 'user-profile', params: {
                        username: this.$store.state.user.username
                    }});
                }
            });
        }
    }
}
</script>

<style scoped>
.buttonStyle {
    font-size: 1.5rem;
    width: 100%;
    height: 100%;
    padding: 0px;
}
</style>