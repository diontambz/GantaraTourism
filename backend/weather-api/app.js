const express = require('express');
const axios = require('axios');
const requestPromise = require('request-promise');
const app = express();
const PORT = process.env.PORT || 3000;
const OPENWEATHERMAP_API_KEY = 'ad6111536fabfbf8fdc994c25ac0aac5';

app.use(express.json());

app.get('/weather', async (req, res) => {
    try {
        let city;

        if (!req.query.city) {
            const ipInfoResponse = await requestPromise('http://ipinfo.io/json');
            const ipInfo = JSON.parse(ipInfoResponse);
            city = ipInfo.city || 'Jakarta';
        } else {
            city = req.query.city;
        }

        const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${OPENWEATHERMAP_API_KEY}`;
        const response = await axios.get(apiUrl);

        const temperatureCelsius = response.data.main.temp - 273.15;

        const weatherData = {
            city: response.data.name,
            temperature: temperatureCelsius.toFixed(1), 
            condition: response.data.weather[0].description,
        };

        res.json(weatherData);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Internal Server Error' });
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});