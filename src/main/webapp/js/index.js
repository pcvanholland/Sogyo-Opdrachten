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

            <div class="taipan-table">

                <div class="player-area"
                    v-for="player in gameState.players">
                    Player: {{ player.id }}

                    <div class="taipan-sets">
                        Play as:
                        <div v-for="type in playTypes">
                            <button v-on:click="playCards(type)">
                                {{ type }}
                            </button>
                        </div>

                    </div>

                    <div v-for="card in player.cards">
                        <input type="checkbox"
                            :value="card.suit+','+card.rank"
                            v-model="checkedCards"
                            @change="chooseCard()">
                            {{ card.suit }}, {{ card.rank }}
                        </input>
                    </div>

                    <button v-on:click="drawCards(player.id)"
                    >Draw Cards</button>
                </div>

            </div>

        </div>
    `,
    methods: {
        drawCards(player)
        {
            this.$emit('draw-cards', player);
        },
        chooseCard()
        {
            this.$emit('choose-card', this.checkedCards);
        },
        playCards(type)
        {
            this.$emit('play-cards', this.checkedCards, type);
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
        async drawCards(playerID)
        {
            const response = await fetch('api/drawcards', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: playerID//this.playerID
            });
            const result = await response.json();
            console.log(result);
            this.gameState = result;
        },
        async chooseCard(activeCards)
        {
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
        async playCards(cards, type)
        {console.log("play called: " + cards + " as " + type);
            const response = await fetch('api/playcards', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "playerID": 0,//this.playerID
                    "cards": JSON.stringify(cards),
                    "type": type
                })
            });
            const result = await response.json();
            console.log(result);
            this.gameState = result;
        }
    }
});
