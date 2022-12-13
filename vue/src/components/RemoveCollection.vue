<template>
    <div class="remove-collection">
        <icon-button 
            buttonText="Delete"
            iconName="delete"
            v-on:click="confirming = true"
        />
        <!-- <app-button v-on:click="confirming = true" buttonText="Delete" /> -->
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
//import AppButton from './Button.vue';
import Confirm from './Confirm.vue'
import IconButton from './IconButton.vue'

export default {
    name: 'remove-collection',
    components: { Confirm, IconButton },
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

<style>
.remove-collection {
    background-color: transparent;
    border-radius: 10px;
}

.remove-collection > div {
    background-color: transparent;
    border-radius: inherit;
}
</style>