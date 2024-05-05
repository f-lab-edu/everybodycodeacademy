import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Card } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const BoardView = () => {
    const { idx } = useParams();
    const [board, setBoard] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/board/detail/${idx}`);
                setBoard(response.data);
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [idx]);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error.message}</div>;
    if (!board) return <div>No data found.</div>;

    return (
        <Card className="my-3">
            <Card.Header>게시글 상세보기</Card.Header>
            <Card.Body>
                <Card.Title>{board.title}</Card.Title>
                <Card.Text>
                    {board.content}
                </Card.Text>
                <div>
                    <strong>작성자: </strong>{board.writername}
                </div>
                <div>
                    <strong>작성일: </strong>{board.regDate}
                </div>
            </Card.Body>
        </Card>
    );
};

export default BoardView;
