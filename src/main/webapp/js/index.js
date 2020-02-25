/**
 * The screen shown before the game starts, prompting players to enter their names.
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
            <p>This is the home page for a simple sample Tai-Pan application.</p>
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
                placeholder="p@$$w0rd"
                type="password"
                accept-charset=ASCII
                v-model="password"
            />

            {{ errorMessage }}

            <button v-on:click="confirmPlayers"
            >Login</button>
        </div>
    `,
    methods: {
        confirmPlayers()
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
            // Try-to-login code here.

            this.errorMessage = "";

            this.$emit('player-confirmed', this.playerName, this.password);
        }
    }
});

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

Vue.component('game-screen', {
    props: [ 'gameState' ],
    template: `
        <div href="startgame">
            Game screen.
        </div>
    `
});

const app = new Vue({
    el: '#app',

    data: {
        playerID: undefined,
        gameState: undefined,
        lobby: undefined,
    },

    computed: {
        isLoggedIn()
        {
            return this.playerID != undefined;
        },
        gameStarted()
        {
            return this.gameState != undefined;
        }
    },

    methods: {
        async login(playerName, password)
        {
            const response = await fetch('api/login', {
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
        async startAGame()
        {
            const response = await fetch('api/startgame', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "playerID": this.playerID
                })
            });
            //const result = await response.json();
            //this.gameState = result;
            this.gameState = true;
        }
    }
});
