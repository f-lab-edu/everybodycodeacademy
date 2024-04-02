import logo from './logo.svg';
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import BoardList from './components/board/boardList';
import BoardView from './components/board/boardView';

function App() {
  return (
      <Router>
        <Routes>
          <Route path="/board/list" element={<BoardList />} />
          <Route path="/board/view/*" element={<BoardView />} />
        </Routes>
      </Router>
  );
}

export default App;
