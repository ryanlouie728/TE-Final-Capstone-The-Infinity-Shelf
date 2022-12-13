<template>
    <div class="trade-card">
        <div v-bind:class="{selected: showDetails}" v-on:click.prevent="tradeClicked($event.path.find(element => {
            return element.classList.contains('trade-card');   
        }))" class="trade-summary">
            <h3>{{this.$store.state.user.username}} ({{userGaveComics.length}})</h3>
            <div class="icon-holder">
                <i v-if="!showDetails" class="material-icons">sync_alt</i>
            </div>
            <h3>{{otherUser}} ({{userGainComics.length}})</h3>
        </div>
        
        <div v-bind:id="trade.id" v-show="showDetails" class="trade-detail">
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
            v-if="recipient == this.$store.state.user.id && showDetails && showButtons"
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
        <div 
            v-if="recipient != this.$store.state.user.id && showDetails && showButtons"
            class="cancel"
        >
            <app-button
                buttonText="Cancel"
                v-on:click="startConfirm('Cancel Trade?', cancel)"
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
    props: ['trade', 'showButtons'],
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
        },
        cancel() {
            TradeService.cancelTrade(this.trade.tradeId)
            .then(response => {
                if (response.status == 200) {
                    this.$emit('refresh');
                }
            })
        },
        hide(card) {
            card.children[1].classList.remove('stretch-out')
            for (let child of card.children[1].children) {
                child.classList.remove('stretch-out');
            }
            this.showDetails = false;
        },
        tradeClicked(card) {
            if (!this.showDetails) {
                this.showDetails = true;
            } else {
                card.children[1].classList.add('stretch-out')
                for (let child of card.children[1].children) {
                    child.classList.add('stretch-out');
                }
                setTimeout(this.hide, 100, card);
            }
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
    color: white;
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
    padding: 5px;
    overflow-y: auto;
    border-radius: 10px;
    height: 225px;
    border: solid 1px var(--medium-accent);
    width: 45%;
    display: flex;
    flex-direction: column;
    align-items: center;
    animation: stretch-in 0.1s linear, scroll-bar-off 0.1s linear;
}
.accept-reject {
    margin-top: 10px;
}

.accept-reject > button {
    margin-right: 5px;
    width: 100px;
}

.cancel {
    margin-top: 10px;
}

.cancel > button {
    margin-right: 5px;
    width: 100px;
}

.stretch-out {
    animation: stretch-out 0.1s forwards;
}

@keyframes scroll-bar-off {
    0% {
        overflow-y: hidden;
    }
    99% {
        overflow-y: hidden;
    }
    100% {
        overflow-y: auto;
    }
}

@keyframes stretch-in {
  from {
    height: 0px;
  }
  to {
    height: 225px;
  }
}

@keyframes stretch-out {
    from {
        height: 225px;
    }
    to {
        height: 0px;
    }
}

</style>