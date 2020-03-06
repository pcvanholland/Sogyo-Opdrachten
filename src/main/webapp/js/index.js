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
        <div class="start-screen-text">
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
    data()
    {
        return {}
    },
    template: `
        <div class="lobbyscreen">
            <button v-on:click="startGame">
                Start a new game.
            </button>

            <button v-on:click="refresh">
                Refresh the game list.
            </button>

            <div class="game-list">
                <div v-for="game in lobby">
                    <button v-on:click="joinGame(game.id)">
                        {{ game.full == true ? "Observe " : "Join " }}
                        {{ game.host }}!
                    </button>
                </div>
            </div>
        </div>
    `,
    methods: {
        startGame()
        {
            this.$emit('game-started');
        },
        joinGame(gameID)
        {
            this.$emit('game-joined', gameID);
        },
        refresh()
        {
            this.$emit('refresh');
        }
    }
});

/**
 * Where all the cards are shown.
 */
Vue.component('game-screen', {
    props: [ 'gameState', 'playTypes', 'playerId', 'playerName' ],
    data()
    {
        return {
            checkedCards: [[],[],[],[]],
        }
    },
    template: `
        <div class="gamescreen">

            <div class="taipan-table">

                <div class="player-area"
                    v-for="player in gameState.players">
                    {{ player.id == playerId ? playerName : "Player " + player.id }}
                        {{ player.inTurn ? "In turn." : "" }}
                    <button v-on:click="passTurn(player.id)"
                        :hidden="!mayControl(player.id)"
                        :disabled="!player.mayPass">
                        Pass turn
                    </button>

                    <div class="taipan-sets">
                        Play as:
                        <div v-for="type in playTypes">
                            <button v-on:click="playCards(type,player.id)"
                                :hidden="!mayControl(player.id)">
                                {{ type }}
                            </button>
                        </div>

                    </div>

                    <div v-for="card in player.cards">
                        <label>
                            <input type="checkbox"
                                :value="card.suit+','+card.rank"
                                v-model="checkedCards[player.id]"
                                @change="chooseCard(player.id)"
                                :hidden="!mayControl(player.id)">
                            </input>
                            {{ maySee(player.id) ?
                                card.suit + ", " + card.rank :
                                "<< Card >>" }}
                        </label>
                    </div>

                    <button v-on:click="drawCards(player.id)"
                        :hidden="!player.canDraw || !mayControl(player.id)">
                        Draw Cards
                    </button>
                </div>

            </div>

            <button v-on:click="refresh"
            >Refresh</button>

            <div class="game-table"
                v-for="play in gameState.table.trick.slice().reverse()">
                <div class="play"
                    v-for="card in play.cards">
                    {{ card.suit }}, {{ card.rank }}
                </div>
            </div>

        </div>
    `,
    methods: {
        maySee(refID)
        {
            return refID == this.playerId || this.playerId == "0" || this.playerId == -1;
        },
        mayControl(refID)
        {
            return refID == this.playerId || this.playerId == "0";
        },
        refresh()
        {
            this.$emit('refresh');
        },
        drawCards(player)
        {
            this.$emit('draw-cards', player);
        },
        chooseCard(player)
        {
            this.$emit('choose-card', this.checkedCards[player]);
        },
        passTurn(player)
        {
            this.$emit('pass-turn', player);
        },
        playCards(type, player)
        {
            this.$emit('play-cards', this.checkedCards[player], type, player);
            this.checkedCards[player] = [];
        }
    }
});

const app = new Vue({
    el: '#app',

    data: {
        playerId: undefined,
        playerName: undefined,
        gameID: undefined,
        gameState: undefined,
        lobby: [],
        playTypes: [],
    },

    computed: {
        isLoggedIn()
        {
            return this.playerName != undefined;
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
                this.playerName = playerName;
            }
            this.refreshLobby();
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
            this.login(playerName, password);
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
            this.playerName = undefined;
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
                    "playerName": this.playerName
                })
            });
            const result = await response.json();
            console.log(result.result);
            this.getGameState();
            this.gameID = gameID;
            this.playerId = result.result;
        },
        async startAGame()
        {
            const response = await fetch('api/lobby/startgame', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: this.playerName
            });
            const result = await response.json();
            console.log(result.result);
            this.getGameState();
            this.gameID = result.result;

            // Host is always first Player (0).
            this.playerId = 0;
        },
        async refreshLobby()
        {
            const response = await fetch('api/lobby/listgames', {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
            const result = await response.json();
            console.log(result);
            this.lobby = result;
        },
        async getGameState()
        {
            const response = await fetch('api/game/getgamestate', {
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
        async drawCards(player)
        {
            const response = await fetch('api/game/drawcards', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: this.playerId == 0 ? player : this.playerId
            });
            const result = await response.json();
            console.log(result);
            this.gameState = result;
        },
        async chooseCard(activeCards)
        {
            const response = await fetch('api/game/getplaytypes', {
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
        async passTurn(player)
        {
            const response = await fetch('api/game/passturn', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: this.playerId == 0 ? player : this.playerId
            });
            const result = await response.json();
            console.log(result);
            this.gameState = result;
        },
        async playCards(cards, type, player)
        {
            const response = await fetch('api/game/playcards', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "playerID": this.playerId == 0 ? player : this.playerId,
                    "cards": JSON.stringify(cards),
                    "type": type
                })
            });
            const result = await response.json();
            console.log(result);
            this.gameState = result;
            this.playTypes = [];
        }
    }
});
