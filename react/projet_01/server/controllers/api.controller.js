const axios = require('axios');


module.exports.getGames = async (req, res) => {

    // https://api.rawg.io/api/games?key=YOUR_API_KEY
    try {
        
        const response = await axios({
            method: 'get',
            url: 'https://api.rawg.io/api/games',
            headers: {
                'Content-Type': 'application/json'            
            },
            params: {
                key: process.env.RAWG_API_KEY
            }
        })

        res.status(200).json(response.data.results);
    } catch (error) {
        res.status(400).json(error);
    }


}


module.exports.getGamesByName = async (req, res) => {

    const search = req.params.name;

    if (!search) return res.status(400).json({ message: 'No parameters was given !' });

    try {
        
        const response = await axios({
            method: 'get',
            url: 'https://api.rawg.io/api/games',
            headers: {
                'Content-Type': 'application/json'            
            },
            params: {
                key: process.env.RAWG_API_KEY,
                search: search
            }
        })

        res.status(200).json(response.data.results);
    } catch (error) {
        res.status(400).json(error);
    }
}