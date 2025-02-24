import axios from "axios";

const API_GATEWAY_URL = "http://localhost:8080"; // Gateway URL

export const api = axios.create({
    baseURL: API_GATEWAY_URL,
    headers: {
        "Content-Type": "application/json",
    },
});
