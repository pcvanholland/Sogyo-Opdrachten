/**
 * The screen shown before the game starts,
 * prompting players to enter their names.
 */
Vue.component('start-screen', {
    data()
    {
        return {
            playerName: undefined,
            password: undefined,
            errorMessage: "",
        }
    },
    template: `
        <div>
            <p>This is the home page for a simple Tai-Pan application.</p>
            <p>Please enter your (nick)name to continue:</p>
            <input
                id="playernameform"
                placeholder="(Nick)name)"
                type="text"
                accept-charset=ASCII
                minlength="3"
                maxlength="20"
                v-model="playerName"
            />
            <input
                id="passwordform"
                placeholder="p@$$w0rd!"
                type="password"
                accept-charset=ASCII
                v-model="password"
            />

            {{ errorMessage }}

            <button v-on:click="confirmPlayer"
            >Login</button>

            <button v-on:click="registerPlayer"
            >Register</button>

            <button v-on:click="unregisterPlayer"
            >Unregister</button>
        </div>
    `,
    methods: {
        confirmPlayer()
        {
            if (!this.playerName)
            {
                this.errorMessage = "Player name is required.";
                return;
            }
            if (!this.password)
            {
                this.errorMessage = "Password is required.";
                return;
            }

            this.errorMessage = "";

            this.$emit('player-confirmed', this.playerName, this.password);
        },
        registerPlayer()
        {
            if (!this.playerName)
            {
                this.errorMessage = "Player name is required.";
                return;
            }
            if (!this.password)
            {
                this.errorMessage = "Password is required.";
                return;
            }

            this.errorMessage = "";

            this.$emit('register-player', this.playerName, this.password);
        },
        unregisterPlayer()
        {
            if (!this.playerName)
            {
                this.errorMessage = "Player name is required.";
                return;
            }
            if (!this.password)
            {
                this.errorMessage = "Password is required.";
                return;
            }

            this.errorMessage = "";

            this.$emit('unregister-player', this.playerName, this.password);
        }
    }
});

/**
 * Where Players can select a game to join.
 */
Vue.component('lobby-screen', {
    props: [ 'lobby' ],
    template: `
        <div>
            Lobby screen.
            <button v-on:click="startGame""
            >Start!</button>
        </div>
    `,
    methods: {
        startGame()
        {
            this.$emit('game-started');
        }
    }
});

/**
 * Where all the cards are shown.
 */
Vue.component('game-screen', {
    props: [ 'gameState', 'playTypes' ],
    data()
    {
        return {
            checkedCards: [],
        }
    },
    template: `
        <div>
            Game screen.

            <div class="taipan-sets">

                <ul id="sets-player0">
                    <li v-for="type in playTypes">
                        <button>
                            {{ type }}
                        </button>
                    </li>
                </ul>

            </div>

            <div class="taipan-table">

                <ul id="cards-player0">
                    <li v-for="card in gameState.players[0].cards">
                        <input type="checkbox"
                            :value="card.suit+','+card.rank"
                            v-model="checkedCards"
                            @change="chooseCard()">
                            {{ card.suit }}, {{ card.rank }}
                        </input>
                    </li>
                </ul>

            </div>

            <button v-on:click="drawCards"
            >Draw Cards</button>

            <button v-on:click="playCards(playTypes)"
                :disabled="playTypes.length == 0"
            >Play Cards</button>

        </div>
    `,
    methods: {
        drawCards()
        {
            this.$emit('draw-cards');
        },
        chooseCard()
        {
            this.$emit('choose-card', this.checkedCards);
        },
        playCards(playTypes)
        {
            this.$emit('play-cards', this.checkedCards);
        }
    }
});

const app = new Vue({
    el: '#app',

    data: {
        playerID: undefined,
        gameID: undefined,
        gameState: undefined,
        lobby: undefined,
        playTypes: [],
    },

    computed: {
        isLoggedIn()
        {
            return this.playerID != undefined;
        },
        gameJoined()
        {
            return this.gameID != undefined;
        }
    },

    methods: {
        async login(playerName, password)
        {
            const response = await fetch('api/user/login', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: playerName,
                    password: password
                })
            });
            const result = await response.json();
            console.log(result.result);
            if (result.result && result.result == playerName)
            {
                this.playerID = playerName;
            }
        },
        async register(playerName, password)
        {
            const response = await fetch('api/user/register', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: playerName,
                    password: password
                })
            });
            const result = await response.json();
            console.log(result.result);
            if (result.result && result.result == playerName)
            {
                this.playerID = playerName;
            }
        },
        async unregister(playerName, password)
        {
            const response = await fetch('api/user/unregister', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: playerName,
                    password: password
                })
            });
            const result = await response.json();
            console.log(result.result);
            this.playerID = undefined;
        },
        async joinGame(gameID)
        {
            const response = await fetch('api/lobby/join', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "gameID": gameID,
                    "playerID": this.playerID
                })
            });
            const result = await response.json();
            console.log(result.result);
            this.gameID = result.result;
        },
        async startAGame()
        {
            const response = await fetch('api/lobby/startgame', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "playerID": this.playerID
                })
            });
            const result = await response.json();
            console.log(result.result);
            this.getGameState();
            this.gameID = result.result;
        },
        async getGameState()
        {
            const response = await fetch('api/getgamestate', {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
            const result = await response.json();
            console.log(result);
            this.gameState = result;
        },
        async drawCards()
        {
            const response = await fetch('api/drawcards', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: 0//this.playerID
            });
            const result = await response.json();
            console.log(result);
            this.gameState = result;
        },
        async chooseCard(activeCards)
        {console.log(activeCards);
            const response = await fetch('api/getplaytypes', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(activeCards)
            });
            const result = await response.json();
            console.log(result);
            this.playTypes = result.sets;
        },
        async playCards(cards)
        {console.log("play called: " + cards);/*
            const response = await fetch('api/drawcards', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: 0//this.playerID
            });
            const result = await response.json();
            console.log(result);
            this.gameState = result;*/
        }
    }
});
