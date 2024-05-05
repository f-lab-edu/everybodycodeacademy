import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import boardView from "./boardView";

const BoardList = () => {
  const [boards, setBoards] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const response = await axios.get('http://localhost:8080/board/list');
        setBoards(response.data);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
      <Table striped bordered hover>
        <thead>
        <tr>
          <th>#</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        {boards.map((board, index) => (
            <tr key={board.idx} ><Link to="/board/detail/${board.idx}"> </Link>
              <td>{index + 1} </td>
              <td><Link to={`/board/view/${board.idx}`} > {board.title}</Link></td>
              <td>{board.writername}</td>
              <td>{board.regDate}</td>
            </tr>
        ))}
        </tbody>
      </Table>
  );

};

export default BoardList;