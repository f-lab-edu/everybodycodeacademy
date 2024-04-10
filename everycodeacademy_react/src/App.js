import logo from './logo.svg';
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import BoardList from './components/board/boardList';
import BoardView from './components/board/boardView';
import SignIn from "./components/member/signIn";

function App() {
  return (
      <Router>
        <Routes>
          <Route path="/board/list" element={<BoardList />} />
          <Route path="/board/view/*" element={<BoardView />} />
          <Route path="/member/signin" element={<SignIn />} />
        </Routes>
      </Router>
  );
}

export default App;
