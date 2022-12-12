<template>
    <div class="trade-card">
        <div v-bind:class="{selected: showDetails}" v-on:click.prevent="showDetails = !showDetails" class="trade-summary">
            <h3>{{this.$store.state.user.username}} ({{userGaveComics.length}})</h3>
            <div class="icon-holder">
                <i v-if="!showDetails" class="material-icons">sync_alt</i>
            </div>
            <h3>{{otherUser}} ({{userGainComics.length}})</h3>
        </div>
        
        <div v-if="showDetails" class="trade-detail">
            <div class="trade-user">
                <comic-list
                    v-bind:comics="userGaveComics"
                />
            </div>
            <i class="material-icons">sync_alt</i>
            <div class="trade-user">
                <comic-list
                    v-bind:comics="userGainComics"
                />
            </div>
        </div>

        <div 
            v-if="recipient == this.$store.state.user.id && showDetails"
            class="accept-reject"
        >
            <app-button
                buttonText="Accept"
                v-on:click="startConfirm('Accept Trade?', accept)"
            />
            <app-button 
                buttonText="Reject"
                v-on:click="startConfirm('Reject Trade?', reject)"
            />

            
        </div>
        <confirm 
            v-if="confirming"
            :message="confirmMessage"
            :function="confirmFunction"
            :arguments="[]"
            @cancel="confirming = false"
        />
        
    </div>
</template>

<script>
import ComicList from '../ComicList.vue'
import AppButton from '../Button.vue'
import Confirm from '../Confirm.vue'
import TradeService from '../../services/TradeService'


export default {
    components: { ComicList, AppButton, Confirm },
    name: 'trade-card',
    props: ['trade'],
    data() {
        return {
            recipient: 0,
            confirming: false,
            showDetails: false,
            userGaveComics: [],
            userGainComics: [],
            otherUser: '',
            confirmMessage: '',
            confirmFunction: ''
        }
    },
    methods: {
        setComics() {
            this.recipient = this.trade.users.find(user => {               
                return user.role == 'recipient';
            }).userDto.id
            this.comics = [];
            for (let comic of this.trade.comics) {
                console.log(comic)
                if (comic.from.username == this.$store.state.user.username) {
                    this.userGaveComics.push(comic.comicDto);
                    this.otherUser = comic.to.username
                } else {
                    this.userGainComics.push(comic.comicDto)
                    this.otherUser = comic.from.username
                }
                
            }
        },
        startConfirm(message, newFunction) {
            this.confirmMessage = message;
            this.confirmFunction = newFunction;
            this.confirming = true;
        },
        accept() {
            TradeService.acceptTrade(this.trade.tradeId)
            .then(response => {
                if (response.status == 200) {
                    this.$emit('refresh');
                }
            })
        },
        reject() {
            TradeService.rejectTrade(this.trade.tradeId)
            .then(response => {
                if (response.status == 200) {
                    this.$emit('refresh');
                }
            })
        }
    },
    created() {
        this.setComics();
    }
}
</script>

<style scoped>
.trade-card {
    width: 100%; 
}

.trade-summary {
    user-select: none;
    cursor: pointer;
    border-radius: 10px;
    width: 100%;
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 5px 0;
    background-color: var(--light-accent);
}

.trade-summary:hover {
    background-color: var(--medium-accent);
    color: var(--white);
}

.selected {
    background-color: var(--dark-accent);
    color: var(--white);
}

.selected:hover {
    background-color: var(--dark-accent);
    color: var(--white);
}

.icon-holder {
    width: 10%;
    display: flex;
    align-items: center;
    justify-content: center;
}

.trade-summary > h3 {
    width: 45%;
    text-align: center;
}

.trade-detail {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
}

.trade-user {
    overflow-y: auto;
    border-radius: 10px;
    height: 225px;
    border: solid 1px var(--medium-accent);
    width: 45%;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.accept-reject {
    margin-top: 10px;
}

.accept-reject > button {
    margin-right: 5px;
    width: 100px;
}



</style>