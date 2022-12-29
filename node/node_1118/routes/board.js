const express = require('express');
const path = require('path');

const router = express.Router();

// GET /board 라우터
router.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '../filepost.html'));
});
module.exports = router;