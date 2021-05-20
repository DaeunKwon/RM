import axios from "axios"

export default axios.create({
    baseURL: "http://localhost:8090",
    header: {
        "Content-type": "application/json",
    }
});